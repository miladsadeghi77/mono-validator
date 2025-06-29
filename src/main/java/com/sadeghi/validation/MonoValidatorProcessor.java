package com.sadeghi.validation;

import com.sadeghi.validation.annotation.MonoValidator;
import com.sadeghi.validation.validator.BaseValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;

@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("com.sadeghi.validation.annotation.MonoValidator")
public class MonoValidatorProcessor extends AbstractProcessor {
  private Messager messager ;

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    messager = processingEnv.getMessager();

    List<String> validatorClassNames = new ArrayList<>();
    for (Element element : roundEnv.getElementsAnnotatedWith(MonoValidator.class)) {

      if (element.getKind() == ElementKind.CLASS) {
        TypeElement typeElement = (TypeElement) element;
        validatorClassNames.add(typeElement.getQualifiedName().toString());
      }
    }
    if (!validatorClassNames.isEmpty()) {

      String directory = BaseValidator.class.getName();
      Path serviceFilePath = Path.of(".")
          .resolve("src")
          .resolve("main")
          .resolve("resources")
          .resolve("META-INF")
          .resolve("services")
          .resolve(directory); // The file name itself

      createFile(serviceFilePath);
      writeToFile(serviceFilePath, validatorClassNames);
    }
    return true;
  }

  private void createFile(Path serviceFilePath) {
    messager.printMessage(Kind.NOTE,"Attempting to create file at: " + serviceFilePath.toAbsolutePath());
    try {
      if ( !Files.exists(serviceFilePath)){
        Files.createDirectories(serviceFilePath.getParent());
      }
    } catch (IOException e) {
      messager.printMessage(Diagnostic.Kind.ERROR,"Failed to create service file: " + e.getMessage());
    }
  }

  private void writeToFile(Path serviceFilePath, List<String> implementationFQTNs) {
    try {
      Files.write(serviceFilePath, implementationFQTNs);
      messager.printMessage(Diagnostic.Kind.NOTE, "Service file generated at: " + serviceFilePath);


    } catch (IOException e) {
      messager.printMessage(Diagnostic.Kind.ERROR, "Failed to write service file: " + e.getMessage());
    }
  }
}