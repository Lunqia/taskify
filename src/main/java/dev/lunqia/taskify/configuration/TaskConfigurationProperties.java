package dev.lunqia.taskify.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
@Getter
@Setter
public class TaskConfigurationProperties {
    private Template template;

  @Getter
  @Setter
  public static class Template {
      /** Whether to enable the feature of creating multiple tasks from a template. */
      private boolean allowMultipleTasks;
  }
}
