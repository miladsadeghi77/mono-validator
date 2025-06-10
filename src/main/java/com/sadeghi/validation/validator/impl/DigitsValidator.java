package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.Digits;
import com.sadeghi.validation.validator.BaseValidator;
import java.math.BigDecimal;

public class DigitsValidator implements BaseValidator<Digits,Object> {
  private int integer;
  private int fractional;
  private String message;

  @Override
  public void initialize(Digits constraintAnnotation) {
    this.integer = constraintAnnotation.integer();
    this.fractional = constraintAnnotation.fractional();
    this.message = constraintAnnotation.message()
        .replace("{integer}", String.valueOf(integer))
        .replace("{fraction}", String.valueOf(fractional));
  }

  @Override
  public boolean isValid(Object value) {

    BigDecimal numberValue;


    if (value instanceof BigDecimal) {
      numberValue = (BigDecimal) value;

    } else if (value instanceof Number) {

      numberValue = new BigDecimal(value.toString());
    } else if (value instanceof CharSequence) {
      try {
        numberValue = new BigDecimal(value.toString());

      } catch (NumberFormatException e) {

        return false;
      }
    } else {

      return false;
    }
    int actualIntegerValue = numberValue.precision() - numberValue.scale();
    return integer >= actualIntegerValue && fractional >= actualIntegerValue;
  }
  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Class<Digits> getAnnotationClass() {
    return Digits.class;
  }
}
