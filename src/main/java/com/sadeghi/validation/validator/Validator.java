package com.sadeghi.validation.validator;

import com.sadeghi.validation.annotation.limiter.Digits;
import com.sadeghi.validation.annotation.limiter.IsEmail;
import com.sadeghi.validation.annotation.limiter.Length;
import com.sadeghi.validation.annotation.limiter.NotEmpty;
import com.sadeghi.validation.annotation.limiter.NotNull;
import com.sadeghi.validation.validator.impl.DigitsValidator;
import com.sadeghi.validation.validator.impl.IsEmailValidator;
import com.sadeghi.validation.validator.impl.LengthValidator;
import com.sadeghi.validation.validator.impl.NotEmptyValidator;
import com.sadeghi.validation.validator.impl.NotNullValidator;
import com.sadeghi.validation.violation.LimitationViolation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

public class Validator {

  private final Map<Class<? extends Annotation>, Class<? extends BaseValidator>>
      annotationToValidatorMap = new HashMap<>();

  //todo create ValidatorException

  public Validator() {

    annotationToValidatorMap.put(NotNull.class, NotNullValidator.class);
    annotationToValidatorMap.put(Digits.class, DigitsValidator.class);
    annotationToValidatorMap.put(Length.class, LengthValidator.class);
    annotationToValidatorMap.put(NotEmpty.class, NotEmptyValidator.class);
    annotationToValidatorMap.put(IsEmail.class, IsEmailValidator.class);

    ServiceLoader<BaseValidator> customValidators = ServiceLoader.load(BaseValidator.class);
    for (BaseValidator validatorInstance : customValidators) {
      try {
        // Instantiate the validator to get its annotation type
        // This assumes the validator's getAnnotationType() doesn't need initialize() first
        Class<? extends Annotation> annotationType = validatorInstance.getAnnotationClass();
        if (annotationType != null) {
          // Use the class of the validator instance for the map, not the instance itself
          annotationToValidatorMap.put(annotationType, (Class<? extends BaseValidator>) validatorInstance.getClass());
        }
      } catch (Exception e) {
        System.err.println("Error loading custom validator: " + validatorInstance.getClass().getName() + ". Error: " + e.getMessage());
        e.printStackTrace();
      }
    }

  }

  public static Set<LimitationViolation> validate(Object object) throws IllegalAccessException {

    Set<LimitationViolation> violations = new LinkedHashSet<>(); // Using LinkedHashSet to preserve some order (not guaranteed by spec)

    if (object == null) {
      return violations;
    }

    Class<?> clazz = object.getClass();

    for (Field declaredField : clazz.getDeclaredFields()) {

      declaredField.setAccessible(true);
      Object value = declaredField.get(object);

      for (Annotation annotation : declaredField.getDeclaredAnnotations()) {

        //if (!isValid(declaredField, annotation, value)) {
          Class<? extends BaseValidator> validatorClass = annotationToValidatorMap.get(
              annotation.annotationType());

          if (validatorClass != null) {

            try {
              BaseValidator baseValidator = validatorClass.getDeclaredConstructor()
                  .newInstance();

              baseValidator.initialize(annotation);

              if (!baseValidator.isValid(value)) {
                violations.add(
                    new LimitationViolation(
                        declaredField.getName(), value.toString(), baseValidator.getMessage()
                    )
                );
              }

            } catch (Exception e) {
              System.err.println("Error during validation of field '" + declaredField.getName() +
                  "' with annotation '" + annotation.annotationType().getSimpleName() + "': "
                  + e.getMessage());
              e.printStackTrace();
            }
          }
        }
      //}
    }
    return violations;
  }

}
