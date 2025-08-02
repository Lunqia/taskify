package dev.lunqia.taskify.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Long> {
  @Override
  @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 FROM tasks WHERE id = :id)")
  boolean existsById(@Param("id") Long id);
}
