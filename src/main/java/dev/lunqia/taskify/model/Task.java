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
  private long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private boolean completed;
  private LocalDateTime deadline;

  @Embedded private Audit audit = new Audit();

  @ManyToOne
  @JoinColumn(name = "task_group_id")
  private TaskGroup group;

  public void updateFrom(Task task) {
    description = task.getDescription();
    completed = task.isCompleted();
    deadline = task.getDeadline();
    group = task.getGroup();
  }
}
