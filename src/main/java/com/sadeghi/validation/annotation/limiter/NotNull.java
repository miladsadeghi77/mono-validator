package com.sadeghi.validation.annotation.limiter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD , ElementType.METHOD , ElementType.PARAMETER})
public @interface NotNull {

  String message() default "must not be null";

}
