package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.NotEmpty;
import com.sadeghi.validation.validator.BaseValidator;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class NotEmptyValidator implements BaseValidator<NotEmpty, Object> {

  @Override
  public void initialize(NotEmpty constraintAnnotation) {

  }

  @Override
  public boolean isValid(Object value) {

    switch (value) {
      case null -> {
        return false;
      }
      case String valueString -> {
        return !valueString.trim().isEmpty();
      }
      case Collection valueCollection -> {
        return !valueCollection.isEmpty();
      }
      case Map valueMap -> {
        return !valueMap.isEmpty();
      }
      default -> {
      }
    }

    if (value.getClass().isArray()) return Array.getLength(value) == 0;
    return false;
  }
}
