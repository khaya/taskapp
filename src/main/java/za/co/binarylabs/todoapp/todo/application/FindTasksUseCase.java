package za.co.binarylabs.todoapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.todoapp.todo.domain.TaskRepository;
import za.co.binarylabs.todoapp.todo.domain.Tasks;
import za.co.binarylabs.todoapp.todo.domain.UnknownTaskException;
import za.co.binarylabs.todoapp.todo.domain.UserId;


@Service
public class FindTasksUseCase {

  private final TaskRepository taskRepository;

  public FindTasksUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Tasks execute(UserId id) {
    return taskRepository.findAllByUserId(id);
  }
}
