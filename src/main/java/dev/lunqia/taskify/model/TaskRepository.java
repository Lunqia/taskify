package dev.lunqia.taskify.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface TaskRepository {
  List<Task> findAll();

  Page<Task> findAll(Pageable page);

  boolean existsById(Long id);

  Optional<Task> findById(Long id);

  List<Task> findByCompleted(@Param("state") Boolean completed);

  Task save(Task task);
}
