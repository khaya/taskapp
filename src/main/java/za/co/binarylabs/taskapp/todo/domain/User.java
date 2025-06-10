package za.co.binarylabs.taskapp.todo.domain;

public class User {

  private final UserId userId;
  private final String username;

  public User(UserId userId, String username) {
    this.userId = userId;
    this.username = username;
  }
}
