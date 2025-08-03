package dev.lunqia.taskify.repository;

import dev.lunqia.taskify.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  @Override
  @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 FROM tasks WHERE id = :id)")
  boolean existsById(@Param("id") Long id);

  boolean existsByCompletedIsFalseAndGroup_Id(Long id);
}
