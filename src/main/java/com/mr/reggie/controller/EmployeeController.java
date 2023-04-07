package com.mr.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mr.reggie.common.R;
import com.mr.reggie.entity.Employee;
import com.mr.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    private R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的pwd进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据页面提交的用户名username 查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //3.没查到返回登录失败结果
        if (emp == null){
            return R.error("登陆失败");
        }
        //4.查询密码是否正确
        if (!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        //5. 查看员工是否可用
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }
        //6.登陆成功 将用户id存入Session并返回登陆结果
        request.setAttribute("employee", emp.getId());
        return R.success(emp);
    }
}