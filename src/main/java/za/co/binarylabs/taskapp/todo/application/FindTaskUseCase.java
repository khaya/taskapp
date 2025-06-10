package za.co.binarylabs.taskapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.taskapp.todo.domain.*;


@Service
public class FindTaskUseCase {

  private final TaskRepository taskRepository;

  public FindTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task execute(TaskId id) {
    return taskRepository.findById(id).orElseThrow(() -> new UnknownTaskException(id));
  }
}
