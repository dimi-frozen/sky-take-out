package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    /**
     * 捕获数据库异常
     * 数据库已存在的用户名
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        //Cause: java.sql.SQLIntegrityConstraintViolationException:
        // Duplicate entry '广依诺' for key 'employee.idx_username'
        //捕获的异常
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");//信息用空格隔开分成字符串数组
            String username = split[2];//获取用户名，索引为2
            String msg = username + MessageConstant.ALREADY_EXISTS;//拼接错误信息
            return Result.error(msg);//返回结果
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
