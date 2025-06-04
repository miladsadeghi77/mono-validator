package com.sadeghi.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Digits {

  int integer() default 0;

  int fractional() default 0;

  String message() default "numeric value out of bounds (<{integer} digits>.<{fraction} digits>)";

}
