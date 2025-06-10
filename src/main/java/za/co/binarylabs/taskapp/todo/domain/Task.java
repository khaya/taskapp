package za.co.binarylabs.taskapp.todo.domain;

import java.time.LocalDateTime;

public class Task {
  TaskId id;
  TaskTitle title;
  TaskDescription description;
  LocalDateTime dueDate;
  Priority priority;
  Status status;

  public TaskId id() {
    return id;
  }
  public TaskTitle title() {
    return title;
  }
  public TaskDescription description() {
    return description;
  }
  public LocalDateTime dueDate() {
    return dueDate;
  }
  public Priority priority() {
    return priority;
  }
  public Status status() {
    return status;
  }

  public boolean isOpen() {
    return this.status == Status.OPEN;
  }

  public boolean isOverdue() {
    return this.dueDate.isBefore(LocalDateTime.now());
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
  }

  public static TaskBuilder builder() {
    return new TaskBuilder();
  }

  public static class TaskBuilder {
    private TaskId id;
    private TaskTitle title;
    private TaskDescription description;
    private LocalDateTime dueDate;
    private Priority priority;
    private Status status;

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

    public TaskBuilder dueDate(LocalDateTime dueDate) {
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

    public Task build() {
          return new Task(this);
    }
  }
}
