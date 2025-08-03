package dev.lunqia.taskify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @OneToMany(mappedBy = "project")
  private Set<TaskGroup> taskGroups;

  @OneToMany(mappedBy = "project")
  private Set<ProjectStep> steps;
}
