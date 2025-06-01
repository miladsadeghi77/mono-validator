package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.NotNull;
import com.sadeghi.validation.validator.BaseValidator;

public class NotNullValidator implements BaseValidator<NotNull, Object> {

  @Override
  public void initialize(NotNull constraintAnnotation) {

  }

  @Override
  public boolean isValid(Object value) {
    return value != null;
  }
}
