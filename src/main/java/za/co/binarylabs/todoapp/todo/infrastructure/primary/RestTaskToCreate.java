package za.co.binarylabs.todoapp.todo.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import za.co.binarylabs.todoapp.todo.domain.TaskToCreate;

import java.time.LocalDateTime;

@Schema(name = "taskToCreate", description = "A task to create")
public class RestTaskToCreate {

  private final String title;
  private final String description;
  private final LocalDateTime dueDate;
  private final String priority;

  RestTaskToCreate(String title,String description, LocalDateTime dueDate, String priority) {
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
  public LocalDateTime getDueDate() {
    return dueDate;
  }

  @Schema(description = "Priority of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getPriority() {
    return priority;
  }
}
