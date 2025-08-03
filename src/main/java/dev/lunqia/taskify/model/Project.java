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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
  private Long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @OneToMany(mappedBy = "project")
  private Set<TaskGroup> taskGroups;

  @OneToMany(mappedBy = "project")
  private Set<ProjectStep> steps;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Project project)) return false;
    return new EqualsBuilder()
        .append(id, project.id)
        .append(description, project.description)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(description).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("description", description).toString();
  }
}
