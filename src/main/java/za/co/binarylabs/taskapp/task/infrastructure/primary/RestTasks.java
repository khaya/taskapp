package za.co.binarylabs.taskapp.task.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.Tasks;

import java.util.Collection;

@Schema(name = "tasks", description = "Some tasks")
final class RestTasks {

  private final Collection<RestTask> tasks;

  private RestTasks(Collection<RestTask> tasks) {
    Assert.notNull("tasks", tasks);
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
