package za.co.binarylabs.taskapp.task.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.time.LocalDateTime;

public record TaskToCreate(String title, String description, LocalDateTime dueDate, String priority) {

  public TaskToCreate {
    Assert.notNull("title", title);
    Assert.notNull("description", description);
    Assert.notNull("dueDate", dueDate);
    Assert.notNull("priority", priority);
  }

  public Task create() {
      return Task
        .builder()
        .id(TaskId.newId())
        .title(new TaskTitle(title))
        .description(new TaskDescription(description))
        .dueDate(dueDate)
        .priority(priority)
        .status(Task.Status.OPEN.name())
        .build();

  }

}
