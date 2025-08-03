package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Task;
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
public class GroupTaskReadModel {
  private String description;
  private boolean completed;

  public GroupTaskReadModel(Task task) {
    description = task.getDescription();
    completed = task.isCompleted();
  }
}
