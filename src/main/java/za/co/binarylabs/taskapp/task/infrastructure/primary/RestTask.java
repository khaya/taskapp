package za.co.binarylabs.taskapp.task.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.Task;

import java.time.LocalDateTime;

@Schema(name="task", description = "A task")
final class RestTask {

  private final String id;
  private final String title;
  private final String description;
  private final LocalDateTime dueDate;
  private final String priority;
  private final String status;

  private RestTask(RestTaskBuilder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.description = builder.description;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
    this.status = builder.status;
  }

  @Schema(description = "ID of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getId() {
    return id;
  }

  @Schema(description = "Title of this task", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getTitle() {
    return title;
  }

  @Schema(description = "Description of this task", requiredMode = Schema.RequiredMode.REQUIRED)
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

  @Schema(description = "Status of this task")
  public String getStatus() {
    return status;
  }

  public static RestTask from(Task task){
    Assert.notNull("task", task);
    return new RestTaskBuilder()
      .id(task.id().value())
      .title(task.title().get())
      .description(task.description().get())
      .dueDate(task.dueDate())
      .priority(task.priority().name())
      .status(task.status().name())
      .build();
  }

 static final class RestTaskBuilder {

    private String id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String priority;
    private String status;

    public RestTaskBuilder id(String id) {
      this.id = id;
      return this;
    }

    public RestTaskBuilder title(String title) {
      this.title = title;
      return this;
    }

    public RestTaskBuilder description(String description) {
      this.description = description;
      return this;
    }

    public RestTaskBuilder dueDate(LocalDateTime dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public RestTaskBuilder priority(String priority) {
      this.priority = priority;
      return this;
    }

    public RestTaskBuilder status(String status) {
      this.status = status;
      return this;
    }

    public RestTask build() {
      return new RestTask(this);
    }
  }
}
