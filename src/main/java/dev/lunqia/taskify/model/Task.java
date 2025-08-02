package dev.lunqia.taskify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public void updateFrom(Task task) {
    description = task.getDescription();
    completed = task.isCompleted();
    deadline = task.getDeadline();
  }

  @PrePersist
  private void prePersist() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
