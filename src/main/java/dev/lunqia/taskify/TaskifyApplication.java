package dev.lunqia.taskify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TaskifyApplication implements RepositoryRestConfigurer {
  public static void main(String... args) {
    SpringApplication.run(TaskifyApplication.class, args);
  }

  @Override
  public void configureValidatingRepositoryEventListener(
      ValidatingRepositoryEventListener validatingListener) {
    validatingListener.addValidator("beforeCreate", validator());
    validatingListener.addValidator("beforeSave", validator());
  }

  @Bean
  Validator validator() {
    return new LocalValidatorFactoryBean();
  }
}
