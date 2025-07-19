 package com.sadeghi.validation.annotation.limiter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER})
public @interface Pattern {

    String regexp();
    String message() default "must match (<{regexp} regex>.)";
}

