package dev.lunqia.taskify.controller;

import dev.lunqia.taskify.configuration.TaskConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InfoController {
  private final DataSourceProperties dataSourceProperties;
  private final TaskConfigurationProperties taskConfigurationProperties;

  @GetMapping("/v1/info/url")
  public String getUrl() {
    return dataSourceProperties.getUrl();
  }

  @GetMapping("/v1/info/template/allowMultipleTasks")
  public boolean isAllowMultipleTasks() {
    return taskConfigurationProperties.getTemplate().isAllowMultipleTasks();
  }
}
