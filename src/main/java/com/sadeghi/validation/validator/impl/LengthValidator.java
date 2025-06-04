package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.Length;
import com.sadeghi.validation.validator.BaseValidator;

public class LengthValidator implements BaseValidator<Length, Object> {
  private int min;
  private int max;
  private String message;

  @Override
  public void initialize(Length constraintAnnotation) {
    this.min = constraintAnnotation.min();
    this.max = constraintAnnotation.max();
    this.message = constraintAnnotation.message()
        .replace("{min}", String.valueOf(min))
        .replace("{max}", String.valueOf(max));
  }

  @Override
  public boolean isValid(Object value) {

    if ( value instanceof String stringValue ) {
      return stringValue.length() > min && stringValue.length() < max;
    }
    return false;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
