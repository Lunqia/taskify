package dev.lunqia.taskify.controller;

import dev.lunqia.taskify.model.Task;
import dev.lunqia.taskify.model.TaskRepository;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {
  private final TaskRepository taskRepository;

  @PostMapping("/v1/tasks")
  public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
    log.info("[POST] /v1/tasks - Creating task: {}", task);
    Task savedTask = taskRepository.save(task);
    return ResponseEntity.created(URI.create("/v1/tasks/" + savedTask.getId()))
        .body(savedTask);
  }

  @GetMapping(
      value = "/v1/tasks",
      params = {"!sort", "!page", "!size"})
  public ResponseEntity<List<Task>> getAllTasks() {
    log.info("[GET] /v1/tasks - Unpaginated");
    return ResponseEntity.ok(taskRepository.findAll());
  }

  @GetMapping("/v1/tasks")
  public ResponseEntity<List<Task>> getAllTasks(Pageable page) {
    log.info("[GET] /v1/tasks - Paginated: {}", page);
    return ResponseEntity.ok(taskRepository.findAll(page).getContent());
  }

  @GetMapping("/v1/tasks/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable long id) {
    log.info("[GET] /v1/tasks/{} - Fetching task", id);
    return ResponseEntity.of(taskRepository.findById(id));
  }

  @PutMapping("/v1/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody @Valid Task task) {
    if (!taskRepository.existsById(id)) {
      log.warn("[PUT] /v1/tasks/{} - Task not found", id);
      return ResponseEntity.notFound().build();
    }

    log.info("[PUT] /v1/tasks/{} - Updating task: {}", id, task);
    task.setId(id);
    taskRepository.save(task);
    return ResponseEntity.noContent().build();
  }
}
