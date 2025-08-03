package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Project;
import dev.lunqia.taskify.model.TaskGroup;
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
public class GroupWriteModel {
  private String description;
  private Set<GroupTaskWriteModel> tasks;

  public TaskGroup toTaskGroup(Project project) {
    return TaskGroup.builder()
        .description(description)
        .tasks(tasks.stream().map(GroupTaskWriteModel::toTask).collect(Collectors.toSet()))
        .project(project)
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GroupWriteModel groupWriteModel)) return false;
    return new EqualsBuilder()
        .append(description, groupWriteModel.description)
        .append(tasks, groupWriteModel.tasks)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(description).append(tasks).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("description", description)
        .append("tasks", tasks)
        .toString();
  }
}
