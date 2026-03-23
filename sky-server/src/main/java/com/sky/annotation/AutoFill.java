package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识需要自动填充的属性
 */
@Target(ElementType.METHOD)//意为这个注解(@AutoFill)只能用在方法上
@Retention(RetentionPolicy.RUNTIME)//意为把这个注解(@AutoFill)一直保留到程序运行起来之后
public @interface AutoFill {
    //数据库操作类型：插入、更新
    OperationType value();
}
