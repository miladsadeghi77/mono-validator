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

    if (value == null ) return false;

    if(value instanceof String valueString) return !valueString.trim().isEmpty();

    if (value instanceof Collection valueCollection) return !valueCollection.isEmpty();

    if (value instanceof Map valueMap) return !valueMap.isEmpty();

    if (value.getClass().isArray()) return Array.getLength(value) == 0;
    return false;


  }
}
