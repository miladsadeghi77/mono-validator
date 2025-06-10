package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.IsEmail;
import com.sadeghi.validation.validator.BaseValidator;
import java.util.regex.Pattern;

public class IsEmailValidator implements BaseValidator<IsEmail, String> {
  private String message;

  private static final String EMAIL_REGEX =
      "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@ (?:gmail\\.com|yahoo\\.com|yahoo\\.co\\.uk|yahoo\\.ca|yahoo\\.fr|yahoo\\.de|yahoo\\.es|yahoo\\.in)$";

  private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);


  @Override
  public void initialize(IsEmail constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(String value) {
    if (value == null || value.isEmpty()) return false;

    return pattern.matcher(value).matches();
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Class<IsEmail> getAnnotationClass() {
    return IsEmail.class;
  }
}
