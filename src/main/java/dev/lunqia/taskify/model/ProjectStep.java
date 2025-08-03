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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
  private Long id;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  private int daysToDeadline;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProjectStep projectStep)) return false;
    return new EqualsBuilder()
        .append(id, projectStep.id)
        .append(description, projectStep.description)
        .append(daysToDeadline, projectStep.daysToDeadline)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(description)
        .append(daysToDeadline)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("description", description)
        .append("daysToDeadline", daysToDeadline)
        .append("project", project)
        .toString();
  }
}
