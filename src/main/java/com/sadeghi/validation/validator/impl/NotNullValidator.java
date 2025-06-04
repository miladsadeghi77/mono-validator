package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.NotNull;
import com.sadeghi.validation.validator.BaseValidator;

public class NotNullValidator implements BaseValidator<NotNull, Object> {
  private String message;

  @Override
  public void initialize(NotNull constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object value) {
    return value != null;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
