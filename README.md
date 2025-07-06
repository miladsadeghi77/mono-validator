
# mono-validator ☕️

A lightweight annotation-based validation framework for Java, leveraging compile-time annotation processing to generate custom validators with ease.

## 📌 Features

- ✅ Custom annotation support
- 🔍 Compile-time validation via annotation processing
- 🧩 Pluggable and extensible design
- 🚫 No runtime reflection overhead
- 🛠️ Easy to integrate with your own validation logic

## 📦 Installation

Clone the repository and install it via Maven or Gradle into your local repository.

```bash
git clone https://github.com/miladsadeghi77/mono-validator.git
cd mono-validator
./gradlew publishToMavenLocal
```

Then include it in your Java project:

<details>
<summary>Gradle</summary>

```groovy
dependencies {
    annotationProcessor 'com.sadeghi.validation:mono-validator:<version>'
    implementation 'com.sadeghi.validation:mono-validator:<version>'
}
```
</details>

<details>
<summary>Maven</summary>

```xml
<dependency>
    <groupId>com.sadeghi.validation</groupId>
    <artifactId>mono-validator</artifactId>
    <version>{version}</version>
</dependency>
```
</details>

## ✨ Usage

### 1. Create a Custom Annotation

```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Max {

  int max() default Integer.MAX_VALUE;
  String message() default "Field must be lower then '{max}' characters.";

}

```

### 2. Implement the Validator

```java

@MonoValidator
public class MaxValidator implements BaseValidator<Max,Integer> {
  private Integer max;
  private String message;
  @Override
  public void initialize(Max constraintAnnotation) {
    max = constraintAnnotation.max();
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Integer value) {
    return value > max;

  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Class<Max> getAnnotationClass() {
    return Max.class;
  }

}
```

### 3. Annotate Your DTO

```java
public class CarDto {
 @Max(max = 200)
  private int speed;

    // getters and setters
}
```

### 4. Run the Validator (Example)

```java
Validator validator = new Validator();
Set<LimitationViolation> result = validator.validate(new CarDto());

result.forEach(System.out::println);

```

## 🧪 Tests

To run the tests:

```bash
./gradlew test
```

## 🧠 How It Works

- Uses Java's `javax.annotation.processing` to generate validation logic at compile-time.
- Validators implement the `BaseValidator` interface.
- Annotated elements are detected and validated based on registered rules.

## 📁 Project Structure

```
mono-validator/
├── annotation/         # Custom annotations
├── processor/          # Annotation processor logic
├── validator/          # Constraint validators
└── tests/              # Unit and integration tests
```

## 🤝 Contributing

Contributions are welcome! Please fork the repo and submit a pull request.

1. Fork it
2. Create your feature branch (`git checkout -b feature/foo`)
3. Commit your changes (`git commit -am 'Add feature'`)
4. Push to the branch (`git push origin feature/foo`)
5. Create a new Pull Request

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 👤 Author

Developed by [Milad Sadeghi](https://github.com/miladsadeghi77)

---

Happy validating! 🧹
