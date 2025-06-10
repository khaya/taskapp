package za.co.binarylabs.todoapp.todo.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import za.co.binarylabs.todoapp.shared.error.domain.Assert;
import za.co.binarylabs.todoapp.todo.domain.Tasks;

import java.util.Collection;

@Schema(name = "tasks", description = "Some tasks")
final class RestTasks {

  private final Collection<RestTask> tasks;

  private RestTasks(Collection<RestTask> tasks) {
    this.tasks = tasks;
  }

  public static RestTasks from(Tasks  tasks) {
    Assert.notNull("tasks", tasks);
    return new RestTasks(tasks.stream().map(RestTask::from).toList());
  }

  public Collection<RestTask> getTasks() {
    return tasks;
  }
}
