package za.co.binarylabs.taskapp.todo.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.Collection;
import java.util.stream.Stream;

public record Tasks(Collection<Task> tasks) {

  public Tasks {
    Assert.notNull("tasks", tasks);
  }

  public Collection<Task> get() {
    return tasks();
  }

  public boolean isEmpty() {
    return tasks.isEmpty();
  }

  public Stream<Task> stream() {
    return tasks().stream();
  }
}
