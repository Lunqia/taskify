package dev.lunqia.taskify.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "tasks")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private boolean completed;
  private LocalDateTime deadline;

  @Builder.Default @Embedded private Audit audit = new Audit();

  @ManyToOne
  @JoinColumn(name = "task_group_id")
  private TaskGroup group;

  public void updateFrom(Task task) {
    description = task.getDescription();
    completed = task.isCompleted();
    deadline = task.getDeadline();
    group = task.getGroup();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Task task)) return false;
    return new EqualsBuilder()
        .append(id, task.id)
        .append(description, task.description)
        .append(completed, task.completed)
        .append(deadline, task.deadline)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(description)
        .append(completed)
        .append(deadline)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("description", description)
        .append("completed", completed)
        .append("deadline", deadline)
        .append("audit", audit)
        .append("group", group)
        .toString();
  }
}
