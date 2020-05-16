package com.github.admins.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    Class<? extends Throwable>[] value() default {};

    boolean isTime() default false;

    boolean isReturn() default true;

}
