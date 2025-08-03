package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Task;
import java.time.LocalDateTime;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GroupTaskWriteModel groupTaskWriteModel)) return false;
    return new EqualsBuilder()
        .append(description, groupTaskWriteModel.description)
        .append(deadline, groupTaskWriteModel.deadline)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(description).append(deadline).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("description", description)
        .append("deadline", deadline)
        .toString();
  }
}
