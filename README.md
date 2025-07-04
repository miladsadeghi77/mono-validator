
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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@MonoValidator
public @interface NotEmpty {
    String message() default "Field must not be empty";
}
```

### 2. Implement the Validator

```java
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
    @Override
    public boolean isValid(String value, NotEmpty annotation) {
        return value != null && !value.trim().isEmpty();
    }
}
```

### 3. Annotate Your DTO

```java
public class UserDto {
    @NotEmpty
    private String name;

    // getters and setters
}
```

### 4. Run the Validator (Example)

```java
ValidationEngine engine = new ValidationEngine();
ValidationResult result = engine.validate(new UserDto());

if (!result.isValid()) {
    result.getErrors().forEach(System.out::println);
}
```

## 🧪 Tests

To run the tests:

```bash
./gradlew test
```

## 🧠 How It Works

- Uses Java's `javax.annotation.processing` to generate validation logic at compile-time.
- Validators implement the `ConstraintValidator` interface.
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
