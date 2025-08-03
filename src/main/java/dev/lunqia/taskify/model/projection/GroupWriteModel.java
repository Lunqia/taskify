package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.TaskGroup;
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
public class GroupWriteModel {
  private String description;
  private Set<GroupTaskWriteModel> tasks;

  public TaskGroup toTaskGroup() {
    return TaskGroup.builder()
        .description(description)
        .tasks(tasks.stream().map(GroupTaskWriteModel::toTask).collect(Collectors.toSet()))
        .build();
  }
}
