package za.co.binarylabs.taskapp.todo.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public record TaskDescription(String description) {

  public TaskDescription {
    Assert.field("description", description).notBlank().maxLength(255);
  }

  public String get(){
    return description();
  }
}
