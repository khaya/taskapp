package za.co.binarylabs.taskapp.todo.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public record TaskTitle(String title) {

  public TaskTitle {
    Assert.field("title", title).notBlank().maxLength(100);
  }

  public String get() {
    return title();
  }
}

