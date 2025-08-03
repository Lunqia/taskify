package dev.lunqia.taskify.service;

import dev.lunqia.taskify.model.TaskGroup;
import dev.lunqia.taskify.model.projection.GroupReadModel;
import dev.lunqia.taskify.model.projection.GroupWriteModel;
import dev.lunqia.taskify.repository.TaskGroupRepository;
import dev.lunqia.taskify.repository.TaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskGroupService {
  private final TaskGroupRepository taskGroupRepository;
  private final TaskRepository taskRepository;

  public GroupReadModel createGroup(GroupWriteModel groupWriteModel) {
    TaskGroup taskGroup = taskGroupRepository.save(groupWriteModel.toTaskGroup());
    return new GroupReadModel(taskGroup);
  }

  public List<GroupReadModel> getAllGroups() {
    return taskGroupRepository.findAll().stream().map(GroupReadModel::new).toList();
  }

  public void toggleGroupCompletion(Long groupId) {
    if (taskGroupRepository.existsByCompletedIsFalseAndProject_Id(groupId)) {
      throw new IllegalStateException("Cannot complete group with incomplete tasks");
    }

    TaskGroup taskGroup =
        taskGroupRepository
            .findById(groupId)
            .orElseThrow(
                () ->
                    new IllegalArgumentException("TaskGroup with ID '" + groupId + "' not found"));
    taskGroup.setCompleted(!taskGroup.isCompleted());
  }
}
