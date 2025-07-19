package com.sadeghi.validation.validator.impl;

import com.sadeghi.validation.annotation.limiter.Pattern;
import com.sadeghi.validation.validator.BaseValidator;

public class PatternValidator implements BaseValidator<Pattern, String> {
    private String message;
    public String regex;

    public void initialize(Pattern limiterAnnotation) {
        this.regex = limiterAnnotation.regexp();
        this.message = limiterAnnotation.message().replace("{regexp}", regex);

    }

    public boolean isValid(String value) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }

    public String getMessage() {
        return message;
    }

    public Class<Pattern> getAnnotationClass() {
        return Pattern.class;
    }
}
