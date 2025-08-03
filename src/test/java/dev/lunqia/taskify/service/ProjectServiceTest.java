package dev.lunqia.taskify.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.lunqia.taskify.configuration.TaskConfigurationProperties;
import dev.lunqia.taskify.model.Project;
import dev.lunqia.taskify.model.ProjectStep;
import dev.lunqia.taskify.model.Task;
import dev.lunqia.taskify.model.TaskGroup;
import dev.lunqia.taskify.model.projection.GroupReadModel;
import dev.lunqia.taskify.model.projection.GroupWriteModel;
import dev.lunqia.taskify.repository.ProjectRepository;
import dev.lunqia.taskify.repository.TaskGroupRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
  @Mock private ProjectRepository projectRepository;
  @Mock private TaskGroupRepository taskGroupRepository;
  @Mock private TaskConfigurationProperties taskConfigurationProperties;
  @Mock private TaskGroupService taskGroupService;
  @InjectMocks private ProjectService toTest;

  @Test
  @DisplayName(
      "Should throw IllegalStateException when only one active group is allowed and one exists")
  void createGroup_noMultipleGroupsConfig_andUndoneGroupExists_throwsIllegalStateException() {
    // GIVEN
    when(taskGroupRepository.existsByCompletedIsFalseAndProjectId(anyLong())).thenReturn(true);

    TaskConfigurationProperties.Template mockTemplate =
        mock(TaskConfigurationProperties.Template.class);
    when(mockTemplate.isAllowMultipleTasks()).thenReturn(false);
    when(taskConfigurationProperties.getTemplate()).thenReturn(mockTemplate);

    // WHEN
    Throwable thrown = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 1L));

    // THEN
    assertThat(thrown)
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Only one active task group is allowed");
  }

  @Test
  @DisplayName("Should create group from project when configuration allows multiple groups")
  void createGroup_multipleGroupsAllowed_createsGroup() {
    // GIVEN
    TaskConfigurationProperties.Template mockTemplate =
        mock(TaskConfigurationProperties.Template.class);
    when(mockTemplate.isAllowMultipleTasks()).thenReturn(true);
    when(taskConfigurationProperties.getTemplate()).thenReturn(mockTemplate);

    ProjectStep step1 = ProjectStep.builder().description("Step 1").daysToDeadline(2).build();
    ProjectStep step2 = ProjectStep.builder().description("Step 2").daysToDeadline(5).build();
    Project project =
        Project.builder().id(1L).description("Test Project").steps(Set.of(step1, step2)).build();

    when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

    Task task1 =
        Task.builder().description("Step 1").deadline(LocalDateTime.of(2025, 8, 5, 12, 0)).build();
    Task task2 =
        Task.builder().description("Step 2").deadline(LocalDateTime.of(2025, 8, 8, 12, 0)).build();
    TaskGroup taskGroup = TaskGroup.builder().tasks(Set.of(task1, task2)).build();

    when(taskGroupService.createGroup(any(GroupWriteModel.class), eq(project)))
        .thenReturn(new GroupReadModel(taskGroup));

    ArgumentCaptor<GroupWriteModel> groupCaptor = ArgumentCaptor.forClass(GroupWriteModel.class);
    LocalDateTime deadline = LocalDateTime.of(2025, 8, 3, 14, 49);

    // WHEN
    toTest.createGroup(deadline, 1L);

    // THEN
    verify(taskGroupService, times(1)).createGroup(groupCaptor.capture(), eq(project));

    GroupWriteModel capturedGroup = groupCaptor.getValue();

    assertThat(capturedGroup.getDescription()).isEqualTo("Test Project");
    assertThat(capturedGroup.getTasks()).hasSize(2);

    assertThat(capturedGroup.getTasks())
        .anySatisfy(
            task -> {
              assertThat(task.getDescription()).isEqualTo("Step 1");
              assertThat(task.getDeadline()).isEqualTo(deadline.plusDays(2));
            })
        .anySatisfy(
            task -> {
              assertThat(task.getDescription()).isEqualTo("Step 2");
              assertThat(task.getDeadline()).isEqualTo(deadline.plusDays(5));
            });
  }
}
