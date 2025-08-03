package dev.lunqia.taskify.adapter;

import dev.lunqia.taskify.model.TaskGroup;
import dev.lunqia.taskify.model.TaskGroupRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskGroupRepository
    extends TaskGroupRepository, JpaRepository<TaskGroup, Long> {
  @Override
  @Query("SELECT DISTINCT tg FROM TaskGroup tg JOIN FETCH tg.tasks")
  List<TaskGroup> findAll();

  @Override
  boolean existsByCompletedIsFalseAndProject_Id(Long projectId);
}
