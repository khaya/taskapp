package za.co.binarylabs.taskapp.task.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public class User {

  private final UserId userId;
  private final String username;

  public User(UserId userId, String username) {
    Assert.notNull("userId", userId);
    Assert.field("username", username).notBlank();
    this.userId = userId;
    this.username = username;
  }
  public UserId userId() {
    return userId;
  }
  public String username() {
    return username;
  }
}
