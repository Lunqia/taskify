package dev.lunqia.taskify.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
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
@Table(name = "task_groups")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private boolean completed;

  @Embedded private Audit audit;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
  private Set<Task> tasks;
}
