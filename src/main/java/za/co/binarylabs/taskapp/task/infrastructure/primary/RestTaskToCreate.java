package za.co.binarylabs.taskapp.task.infrastructure.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.TaskToCreate;

import java.time.LocalDate;

@Schema(name = "taskToCreate", description = "A task to create")
class RestTaskToCreate {

  private final String title;
  private final String description;
  private final LocalDate dueDate;
  private final String priority;

  RestTaskToCreate(@JsonProperty("title") String title,@JsonProperty("description") String description,@JsonProperty("dueDate") LocalDate dueDate,@JsonProperty("priority") String priority) {
    Assert.notNull("title", title);
    Assert.notNull("description", description);
    Assert.notNull("dueDate", dueDate);
    Assert.notNull("priority", priority);
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.priority = priority;
  }

  public TaskToCreate toDomain() {
    return new TaskToCreate(getTitle(), getDescription(), getDueDate(), getPriority());
  }

  @Schema(description = "Title of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getTitle() {
    return title;
  }

  @Schema(description = "Due date of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getDescription() {
    return description;
  }

  @Schema(description = "Due date of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public LocalDate getDueDate() {
    return dueDate;
  }

  @Schema(description = "Priority of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getPriority() {
    return priority;
  }
}
