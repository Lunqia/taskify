package dev.lunqia.taskify.repository;

import dev.lunqia.taskify.model.TaskGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
  @Override
  @Query("SELECT DISTINCT tg FROM TaskGroup tg JOIN FETCH tg.tasks")
  List<TaskGroup> findAll();

  boolean existsByCompletedIsFalseAndProject_Id(Long projectId);
}
