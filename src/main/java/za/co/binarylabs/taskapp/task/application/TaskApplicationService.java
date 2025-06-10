package za.co.binarylabs.taskapp.task.application;

import org.springframework.stereotype.Service;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.*;

import java.util.Optional;

@Service
public class TaskApplicationService {

  private final TaskRepository taskRepository;

  public TaskApplicationService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(TaskToCreate taskToCreate) {
    Assert.notNull("taskToCreate", taskToCreate);
    var task = taskToCreate.create();
    taskRepository.save(task);
    return task;
  }

  public Optional<Task> findTask(TaskId id) {
    Assert.notNull("id", id);
    return taskRepository.findById(id);
  }

  public void completeTask(TaskId taskId) {
    Assert.notNull("taskId", taskId);
    Optional<Task> task = taskRepository.findById(taskId);
    task.ifPresent(t -> {
      t.markAsCompleted();
      taskRepository.save(t);
    });
  }

  public void deleteTask(TaskId taskId) {
    Assert.notNull("taskId", taskId);
    taskRepository.delete(taskId);
  }

  public void updateTask(Task task) {
    Assert.notNull("task", task);
    taskRepository.save(task);
  }

  public Tasks findTasks(UserId userId) {
    Assert.notNull("userId", userId);
    return taskRepository.findAllByUserId(userId);
  }

  public Optional<Task> findTaskById(TaskId taskId) {
    Assert.notNull("taskId", taskId);
    return taskRepository.findById(taskId);
  }

}
