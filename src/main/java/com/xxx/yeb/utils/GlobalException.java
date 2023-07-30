package com.xxx.yeb.utils;

import com.xxx.yeb.entity.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理
 */
@RestControllerAdvice       // 控制器增强类，发送异常符合类中定义的拦截异常就会被拦截
public class GlobalException {

    @ExceptionHandler(SQLException.class)       // 捕捉异常
    public RespBean mySqlException(SQLException e){
        return RespBean.error("数据库连接异常");
    }
}
