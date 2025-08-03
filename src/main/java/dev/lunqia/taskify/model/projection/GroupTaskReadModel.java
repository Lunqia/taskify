package dev.lunqia.taskify.model.projection;

import dev.lunqia.taskify.model.Task;
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
public class GroupTaskReadModel {
  private String description;
  private boolean completed;

  public GroupTaskReadModel(Task task) {
    description = task.getDescription();
    completed = task.isCompleted();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GroupTaskReadModel groupTaskReadModel)) return false;
    return new EqualsBuilder()
        .append(description, groupTaskReadModel.description)
        .append(completed, groupTaskReadModel.completed)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(description).append(completed).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("description", description)
        .append("completed", completed)
        .toString();
  }
}
