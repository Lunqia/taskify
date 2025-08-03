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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GroupReadModel groupReadModel)) return false;
    return new EqualsBuilder()
        .append(description, groupReadModel.description)
        .append(deadline, groupReadModel.deadline)
        .append(tasks, groupReadModel.tasks)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(description)
        .append(deadline)
        .append(tasks)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("description", description)
        .append("deadline", deadline)
        .append("tasks", tasks)
        .toString();
  }
}
