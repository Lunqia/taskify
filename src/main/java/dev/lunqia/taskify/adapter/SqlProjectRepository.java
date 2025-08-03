package dev.lunqia.taskify.adapter;

import dev.lunqia.taskify.model.Project;
import dev.lunqia.taskify.model.ProjectRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Long> {
  @Override
  @Query("SELECT DISTINCT p FROM Project p JOIN FETCH p.steps")
  List<Project> findAll();
}
