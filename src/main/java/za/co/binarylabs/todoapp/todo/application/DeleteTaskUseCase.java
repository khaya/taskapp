package za.co.binarylabs.todoapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.todoapp.todo.domain.TaskId;
import za.co.binarylabs.todoapp.todo.domain.TaskRepository;

@Service
public class DeleteTaskUseCase {

  private final TaskRepository taskRepository;

  public DeleteTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId) {
    taskRepository.delete(taskId);
  }
}
