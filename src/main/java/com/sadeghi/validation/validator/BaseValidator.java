package com.sadeghi.validation.validator;

import java.lang.annotation.Annotation;

public interface BaseValidator<A extends Annotation , T> {
  void initialize(A constraintAnnotation);
  boolean isValid(T value);
  String getMessage();
  Class<A> getAnnotationClass();
}
