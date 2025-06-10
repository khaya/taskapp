package za.co.binarylabs.todoapp.todo.domain;

import za.co.binarylabs.todoapp.shared.error.domain.Assert;

public record TaskTitle(String title) {

  public TaskTitle {
    Assert.field("title", title).notBlank().maxLength(100);
  }

  public String get() {
    return title();
  }
}

