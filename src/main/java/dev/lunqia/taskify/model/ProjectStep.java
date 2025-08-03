package dev.lunqia.taskify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_steps")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStep {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private int daysToDeadline;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;
}
