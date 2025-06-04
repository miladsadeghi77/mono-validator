package com.sadeghi.validation.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsEmail {

  String message() default "Please enter a valid email address (e.g., user@gmail.com or user@yahoo.com)";
}
