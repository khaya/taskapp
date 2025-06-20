package za.co.binarylabs.taskapp.task.domain;

import java.time.LocalDate;
import java.time.LocalDate;

public class Task {
  TaskId id;
  TaskTitle title;
  TaskDescription description;
  LocalDate dueDate;
  Priority priority;
  Status status;
  User user;

  public TaskId id() {
    return id;
  }
  public TaskTitle title() {
    return title;
  }
  public TaskDescription description() {
    return description;
  }
  public LocalDate dueDate() {
    return dueDate;
  }
  public Priority priority() {
    return priority;
  }
  public Status status() {
    return status;
  }
  public User user() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isOpen() {
    return this.status == Status.OPEN;
  }

  public boolean isOverdue() {
    return this.dueDate.isBefore(LocalDate.now());
  }

  public void markAsCompleted() {
      this.status = Status.COMPLETED;
  }

  public enum Priority {
    LOW, MEDIUM, HIGH;

    public static Priority fromString(String value) {
      if (value == null) {
        throw new IllegalArgumentException("Priority value cannot be null");
      }
      return Priority.valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
      return name();
    }
  }

  public enum Status {
    OPEN, IN_PROGRESS, COMPLETED;


    public static Status fromString(String value) {
      if (value == null) {
        throw new IllegalArgumentException("Status value cannot be null");
      }
      return Status.valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
      return name();
    }
  }

  // Private constructor to enforce use of builder
  private Task() {}

  public Task(TaskBuilder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.description = builder.description;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
    this.status = builder.status;
    this.user = builder.user;
  }

  public static TaskBuilder builder() {
    return new TaskBuilder();
  }

  public static class TaskBuilder {
    private TaskId id;
    private TaskTitle title;
    private TaskDescription description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private User user;

    public TaskBuilder(){
    }

    public TaskBuilder id(TaskId id) {
      this.id = id;
      return this;
    }

    public TaskBuilder title(TaskTitle title) {
      this.title = title;
      return this;
    }

    public TaskBuilder description(TaskDescription description) {
      this.description = description;
      return this;
    }

    public TaskBuilder dueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public TaskBuilder priority(String priority) {
      this.priority = Priority.valueOf(priority);
      return this;
    }

    public TaskBuilder status(String status) {
      this.status = Status.valueOf(status);
      return this;
    }
    public TaskBuilder user(User user) {
      this.user = user;
      return this;
    }

    public Task build() {
          return new Task(this);
    }
  }
}
