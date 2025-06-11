package za.co.binarylabs.taskapp.task.infrastructure.primary;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.binarylabs.taskapp.task.application.TaskApplicationService;
import za.co.binarylabs.taskapp.task.domain.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name = "Tasks")
@RequestMapping("/api/tasks")
class TasksResource {

  private final TaskApplicationService applicationService;

  public TasksResource(TaskApplicationService applicationService, TaskRepository taskRepository) {
    this.applicationService = applicationService;
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@Validated @RequestBody TaskToCreate taskToCreate) {
    Task created = applicationService.createTask(taskToCreate);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<Task> findTask(@PathVariable("taskId") UUID taskId) {
    Optional<Task> task = applicationService.findTaskById(new TaskId(taskId));
    return task.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{taskId}/complete")
  public ResponseEntity<Void> completeTask(@PathVariable("taskId") UUID taskId) {
    applicationService.completeTask(new TaskId(taskId));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable("taskId") UUID taskId) {
    applicationService.deleteTask(new TaskId(taskId));
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<Task> updateTask(@Validated @RequestBody Task task) {
    applicationService.updateTask(task);
    return ResponseEntity.ok(task);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<Tasks> findTasksByUserId(@PathVariable("userId") UUID userId) {
    Tasks tasks = applicationService.findTasks(new UserId(userId));
    return ResponseEntity.ok(tasks);
  }
}
