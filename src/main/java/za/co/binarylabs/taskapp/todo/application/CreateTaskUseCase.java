package za.co.binarylabs.taskapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.taskapp.todo.domain.Task;
import za.co.binarylabs.taskapp.todo.domain.TaskRepository;
import za.co.binarylabs.taskapp.todo.domain.TaskToCreate;

@Service
public class CreateTaskUseCase {

  private final TaskRepository taskRepository;

  public CreateTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task execute(TaskToCreate taskToCreate) {
    var task = taskToCreate.create();
    taskRepository.save(task);
    return task;
  }
}
