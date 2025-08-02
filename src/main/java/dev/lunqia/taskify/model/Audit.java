package dev.lunqia.taskify.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @PrePersist
  private void prePersist() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
