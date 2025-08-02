package dev.lunqia.taskify.controller;

import dev.lunqia.taskify.model.Task;
import dev.lunqia.taskify.model.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {
  private final TaskRepository taskRepository;

  @GetMapping(
      value = "/v1/tasks",
      params = {"!sort", "!page", "!size"})
  public ResponseEntity<List<Task>> getAllTasks() {
    log.info("[GET] /v1/tasks");
    return ResponseEntity.ok(taskRepository.findAll());
  }

  @GetMapping("/v1/tasks")
  public ResponseEntity<List<Task>> getAllTasks(Pageable page) {
    log.info("[GET] /v1/tasks {page={}}", page);
    return ResponseEntity.ok(taskRepository.findAll(page).getContent());
  }
}
