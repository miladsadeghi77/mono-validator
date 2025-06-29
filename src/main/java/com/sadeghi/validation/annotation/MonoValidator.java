package com.sadeghi.validation.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface MonoValidator {
   // Class<? extends BaseValidator<?,?>> validatedBy();
}
