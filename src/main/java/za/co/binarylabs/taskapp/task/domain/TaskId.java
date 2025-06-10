package za.co.binarylabs.taskapp.task.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.UUID;

public record TaskId(UUID id) {

  public TaskId {
    Assert.notNull("id", id);
  }

  public static TaskId newId() {
    return new TaskId(UUID.randomUUID());
  }

  public static TaskId fromString(String id) {
    Assert.notNull("id", id);
    return new TaskId(UUID.fromString(id));
  }

  public UUID get() {
    return id;
  }

  public String value() {
    return id.toString();
  }

}
