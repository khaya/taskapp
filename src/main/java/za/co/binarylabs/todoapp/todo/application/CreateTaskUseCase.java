package za.co.binarylabs.todoapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.todoapp.todo.domain.Task;
import za.co.binarylabs.todoapp.todo.domain.TaskRepository;
import za.co.binarylabs.todoapp.todo.domain.TaskToCreate;

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
