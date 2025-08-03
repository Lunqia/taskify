package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Task;
import dev.lunqia.taskify.model.TaskGroup;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupReadModel {
  private String description;

  /** Deadline from the latest task in the group. */
  private LocalDateTime deadline;

  private Set<GroupTaskReadModel> tasks;

  public GroupReadModel(TaskGroup taskGroup) {
    description = taskGroup.getDescription();
    taskGroup.getTasks().stream()
        .map(Task::getDeadline)
        .max(LocalDateTime::compareTo)
        .ifPresent(date -> deadline = date);
    tasks = taskGroup.getTasks().stream().map(GroupTaskReadModel::new).collect(Collectors.toSet());
  }
}
