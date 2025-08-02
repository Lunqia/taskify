package dev.lunqia.taskify;

import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TaskifyApplication {
  public static void main(String... args) {
    SpringApplication.run(TaskifyApplication.class, args);
  }

  @Bean
  Validator validator() {
    return new LocalValidatorFactoryBean();
  }
}
