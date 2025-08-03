package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Task;
import java.time.LocalDateTime;
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
public class GroupTaskWriteModel {
  private String description;
  private LocalDateTime deadline;

  public GroupTaskWriteModel(Task task) {
    description = task.getDescription();
    deadline = task.getDeadline();
  }

  public Task toTask() {
    return Task.builder().description(description).deadline(deadline).build();
  }
}
