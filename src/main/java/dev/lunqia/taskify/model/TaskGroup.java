package dev.lunqia.taskify.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
  private Long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private boolean completed;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
  private Set<Task> tasks;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TaskGroup taskGroup)) return false;
    return new EqualsBuilder()
        .append(id, taskGroup.id)
        .append(description, taskGroup.description)
        .append(completed, taskGroup.completed)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(description)
        .append(completed)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("description", description)
        .append("completed", completed)
        .append("tasks", tasks)
        .append("project", project)
        .toString();
  }
}
