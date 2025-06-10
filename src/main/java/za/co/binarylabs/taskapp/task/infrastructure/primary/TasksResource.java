package za.co.binarylabs.taskapp.task.infrastructure.primary;

import io.swagger.v3.oas.annotations.tags.Tag;
import za.co.binarylabs.taskapp.task.application.*;

import org.springframework.web.bind.annotation.RequestMapping;
import za.co.binarylabs.taskapp.task.domain.TaskRepository;


//@RestController
@Tag(name = "Tasks")
@RequestMapping("/api/tasks")
class TasksResource {

  private final TaskApplicationService applicationService;
  private final TaskRepository taskRepository;

  public TasksResource(TaskApplicationService applicationService, TaskRepository taskRepository) {
    this.applicationService = applicationService;
    this.taskRepository = taskRepository;
  }

/*
  @PostMapping
  @Operation(summary = "Add a Task to the catalog")
  @ApiResponse(description = "Task added to the catalog", responseCode = "201")
  ResponseEntity<RestTask> addTask(@Validated @RequestBody RestTaskToCreate taskToCreate) {
    Task createdTask = createTaskUseCase.execute(taskToCreate.toDomain());
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
  }*/
}
