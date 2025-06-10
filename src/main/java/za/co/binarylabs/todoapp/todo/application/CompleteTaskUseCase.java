package za.co.binarylabs.todoapp.todo.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.todoapp.todo.domain.Task;
import za.co.binarylabs.todoapp.todo.domain.TaskId;
import za.co.binarylabs.todoapp.todo.domain.TaskRepository;
import za.co.binarylabs.todoapp.todo.domain.UnknownTaskException;

@Service
public class CompleteTaskUseCase {

  private final TaskRepository taskRepository;

  public CompleteTaskUseCase(TaskRepository taskRepository){
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId){
    Task task = taskRepository.findById(taskId).orElseThrow(() -> new UnknownTaskException(taskId));
    task.markAsCompleted();
    taskRepository.save(task);
  }
}
