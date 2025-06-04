package com.sadeghi.validation.violation;

public class LimitationViolation {

  private String property;
  private String invalidValue;
  private String message;

  public LimitationViolation(String property, String invalidValue, String message) {
    this.property = property;
    this.invalidValue = invalidValue;
    this.message = message;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

  public void setInvalidValue(String invalidValue) {
    this.invalidValue = invalidValue;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
