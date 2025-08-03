package dev.lunqia.taskify.service;

import dev.lunqia.taskify.configuration.TaskConfigurationProperties;
import dev.lunqia.taskify.model.Project;
import dev.lunqia.taskify.model.projection.GroupReadModel;
import dev.lunqia.taskify.model.projection.GroupTaskWriteModel;
import dev.lunqia.taskify.model.projection.GroupWriteModel;
import dev.lunqia.taskify.repository.ProjectRepository;
import dev.lunqia.taskify.repository.TaskGroupRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final TaskGroupRepository taskGroupRepository;
  private final TaskConfigurationProperties taskConfigurationProperties;
  private final TaskGroupService taskGroupService;

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Project save(Project project) {
    return projectRepository.save(project);
  }

  public GroupReadModel createGroup(LocalDateTime deadline, Long projectId) {
    if (!taskConfigurationProperties.getTemplate().isAllowMultipleTasks()
        && taskGroupRepository.existsByCompletedIsFalseAndProjectId(projectId)) {
      throw new IllegalStateException("Only one active task group is allowed per project");
    }

    return projectRepository
        .findById(projectId)
        .map(
            project -> {
              GroupWriteModel targetGroup =
                  GroupWriteModel.builder()
                      .description(project.getDescription())
                      .tasks(
                          project.getSteps().stream()
                              .map(
                                  projectStep ->
                                      GroupTaskWriteModel.builder()
                                          .description(projectStep.getDescription())
                                          .deadline(
                                              deadline.plusDays(projectStep.getDaysToDeadline()))
                                          .build())
                              .collect(Collectors.toSet()))
                      .build();
              return taskGroupService.createGroup(targetGroup, project);
            })
        .orElseThrow(
            () -> new IllegalArgumentException("Project with ID '" + projectId + "' not found"));
  }
}
