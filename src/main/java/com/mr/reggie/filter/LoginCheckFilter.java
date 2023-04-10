package com.mr.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.mr.reggie.common.BaseContext;
import com.mr.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.PathMatcher;

/**
 * 检查用户是否已经登录
 */

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*") //*所有请求都拦截
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //1.获取本次请求的URI
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到：{}", requestURI);
        String []urls = new String[]{
                "/employee/logout",
                "/employee/login",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.不需要处理，直接放行
        if (check){
            log.info("本次请求不需要处理：{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //4.判断登陆状态，已经登陆则放行
        if (request.getSession().getAttribute("employee") != null){
            log.info("用户已登录, id：{}", request.getSession().getAttribute("employee"));
            //设置线程的用户ID
            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);

            return;
        }
        //5.未登陆，返回登陆结果 输出流方式向客户端响应数据
        log.info("未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 检查URI是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)){
                return true;
            }
        }
        return false;
    }
}
