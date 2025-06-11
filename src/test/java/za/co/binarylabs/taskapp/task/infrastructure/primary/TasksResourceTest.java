package za.co.binarylabs.taskapp.task.infrastructure.primary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.task.application.TaskApplicationService;
import za.co.binarylabs.taskapp.task.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@UnitTest
@DisplayName("TasksResourceTest")
class TasksResourceTest {

  private final TaskApplicationService applicationService = mock(TaskApplicationService.class);
  private final TaskRepository taskRepository = mock(TaskRepository.class);
  private final TasksResource resource = new TasksResource(applicationService, taskRepository);

  @Test
  @DisplayName("CreateTask returns created Task with status CREATED")
  void createTaskReturnsCreatedTaskWithStatusCreated() {
    TaskToCreate toCreate = mock(TaskToCreate.class);

    UUID id = UUID.randomUUID();
    TaskId taskId = new TaskId(id);
    Task task = Task.builder().id(taskId).title(new TaskTitle("title")).description(new TaskDescription("Description")).status("OPEN").priority("HIGH").dueDate(LocalDate.now()).build();

    RestTask restTask = RestTask.from(task);
    when(applicationService.createTask(toCreate)).thenReturn(task);

    ResponseEntity<RestTask> response = resource.createTask(toCreate);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody().getTitle()).isEqualTo(restTask.getTitle());
  }

  @Test
  @DisplayName("FindTask returns Task when found")
  void findTaskReturnsTaskWhenFound() {
    UUID id = UUID.randomUUID();
    TaskId taskId = new TaskId(id);
    Task task = Task.builder().id(taskId).title(new TaskTitle("title")).description(new TaskDescription("Description")).status("OPEN").priority("HIGH").dueDate(LocalDate.now()).build();
    when(applicationService.findTaskById(taskId)).thenReturn(Optional.of(task));

    RestTask restTask = RestTask.from(task);
    when(applicationService.findTaskById(taskId)).thenReturn(Optional.of(task));

    ResponseEntity<RestTask> response = resource.findTask(id);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTitle()).isEqualTo(restTask.getTitle());
  }

  @Test
  @DisplayName("FindTask returns NOT_FOUND when task does not exist")
  void findTaskReturnsNotFoundWhenTaskDoesNotExist() {
    UUID id = UUID.randomUUID();
    when(applicationService.findTaskById(new TaskId(id))).thenReturn(Optional.empty());

    ResponseEntity<RestTask> response = resource.findTask(id);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNull();
  }

  @Test
  @DisplayName("CompleteTask returns OK after completion")
  void completeTaskReturnsOkAfterCompletion() {
    UUID id = UUID.randomUUID();

    ResponseEntity<Void> response = resource.completeTask(id);

    verify(applicationService).completeTask(new TaskId(id));
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNull();
  }

  @Test
  @DisplayName("DeleteTask returns OK after deletion")
  void deleteTaskReturnsOkAfterDeletion() {
    UUID id = UUID.randomUUID();

    ResponseEntity<Void> response = resource.deleteTask(id);

    verify(applicationService).deleteTask(new TaskId(id));
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNull();
  }

  @Test
  @DisplayName("UpdateTask returns updated Task with status OK")
  void updateTaskReturnsUpdatedTaskWithStatusOk() {
    UUID id = UUID.randomUUID();
    TaskId taskId = new TaskId(id);
    Task task = Task.builder().id(taskId).title(new TaskTitle("title")).description(new TaskDescription("Description")).status("OPEN").priority("HIGH").dueDate(LocalDate.now()).build();

    RestTask restTask = RestTask.from(task);

    doNothing().when(applicationService).updateTask(task);


    ResponseEntity<RestTask> response = resource.updateTask(task);

    verify(applicationService).updateTask(task);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTitle()).isEqualTo(restTask.getTitle());
  }

  @Test
  @DisplayName("FindTasksByUserId returns Tasks for given userId")
  void findTasksByUserIdReturnsTasksForGivenUserId() {
    UUID id = UUID.randomUUID();
    TaskId taskId = new TaskId(id);
    Task task = Task.builder().id(taskId).title(new TaskTitle("title")).description(new TaskDescription("Description")).status("OPEN").priority("HIGH").dueDate(LocalDate.now()).build();

    UUID userId = UUID.randomUUID();
    Tasks tasks = new Tasks(List.of(task));
    RestTasks restTasks = RestTasks.from(tasks);

    when(applicationService.findTasks(new UserId(userId))).thenReturn(tasks);

    ResponseEntity<RestTasks> response = resource.findTasksByUserId(userId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTasks().size()).isEqualTo(restTasks.getTasks().size());
  }
}
