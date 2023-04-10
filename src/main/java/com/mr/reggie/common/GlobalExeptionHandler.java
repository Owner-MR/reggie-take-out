package com.mr.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice(annotations = {RestController.class, Controller.class}) //扫描这些类的异常
@ResponseBody
@Slf4j
public class GlobalExeptionHandler {
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class) //捕获该异常
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String msg = ex.getMessage().split(" ")[2];
            return R.error(msg + "已存在");
        }
        return R.error("未知错误");
    }
}
