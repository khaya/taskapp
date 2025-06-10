package za.co.binarylabs.taskapp.todo.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.binarylabs.taskapp.todo.application.*;
import za.co.binarylabs.taskapp.todo.domain.*;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Tasks")
@RequestMapping("/api/tasks")
class TasksResource {

  private final CreateTaskUseCase createTaskUseCase;
  private final CompleteTaskUseCase completeTaskUseCase;
  private final DeleteTaskUseCase deleteTaskUseCase;
  private final FindTasksUseCase findTasksUseCase;
  private final FindTaskUseCase findTaskUseCase;
  private final TaskRepository taskRepository;

  public TasksResource(CreateTaskUseCase createTaskUseCase, CompleteTaskUseCase completeTaskUseCase, DeleteTaskUseCase deleteTaskUseCase, FindTasksUseCase findTasksUseCase, FindTaskUseCase findTaskUseCase, TaskRepository taskRepository) {
    this.createTaskUseCase = createTaskUseCase;
    this.completeTaskUseCase = completeTaskUseCase;
    this.deleteTaskUseCase = deleteTaskUseCase;
    this.findTasksUseCase = findTasksUseCase;
    this.findTaskUseCase = findTaskUseCase;
    this.taskRepository = taskRepository;
  }

  @PostMapping
  @Operation(summary = "Add a Task to the catalog")
  @ApiResponse(description = "Task added to the catalog", responseCode = "201")
  ResponseEntity<RestTask> addTask(@Validated @RequestBody RestTaskToCreate taskToCreate) {
    var createdTask = createTaskUseCase.execute(taskToCreate.toDomain());

    return new ResponseEntity<>(RestTask.from(createdTask), HttpStatus.CREATED);
  }

  @PostMapping("/{task-id}/complete")
  @Operation(summary = "Mark Task as completed")
  @ApiResponse(description = "Task marked as completed", responseCode = "200")
  public ResponseEntity<Void> completeTask(@PathVariable("task-id") UUID id) {
    completeTaskUseCase.execute(new TaskId(id));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{task-id}")
  @Operation(summary = "Get Task by ID")
  @ApiResponse(description = "Task found", responseCode = "200")
  public ResponseEntity<RestTask> findTaskById(@PathVariable("task-id") UUID id) {
    Task task = findTaskUseCase.execute(new TaskId(id));
    return ResponseEntity.ok(RestTask.from(task));
  }

  @GetMapping("/user/{user-id}")
  @Operation(summary = "Get Tasks by User ID")
  @ApiResponse(description = "List of tasks for the user", responseCode = "200")
  public ResponseEntity<RestTasks> getTasksByUserId(@PathVariable("user-id") UUID id) {
    Tasks tasks = findTasksUseCase.execute(new UserId(id));
    return ResponseEntity.ok(RestTasks.from(tasks));
  }

  @DeleteMapping("/{task-id}")
  @Operation(summary = "Remove Task from catalog")
  @ApiResponse(description = "Task removed from the catalog", responseCode = "200")
  void removeTask(@PathVariable("task-id") UUID id) {
    deleteTaskUseCase.execute(new TaskId(id));
  }
}
