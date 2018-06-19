package com.zjh.mysys.annotation;

import com.zjh.mysys.enums.AuthorityNameEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthorityType {
    AuthorityNameEnum[] name();
}
