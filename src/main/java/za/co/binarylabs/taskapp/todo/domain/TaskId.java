package za.co.binarylabs.taskapp.todo.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.UUID;

public record TaskId(UUID id) {

  public TaskId {
    Assert.notNull("id", id);
  }

  public static TaskId newId() {
    return new TaskId(UUID.randomUUID());
  }

  public String get() {
    return id.toString();
  }

}
