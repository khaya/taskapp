package za.co.binarylabs.taskapp.task.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.task.domain.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@UnitTest
@DisplayName("TaskApplicationServiceTest")
class TaskApplicationServiceTest {

  private final TaskRepository taskRepository = mock(TaskRepository.class);
  private final TaskApplicationService service = new TaskApplicationService(taskRepository);

  @Test
  @DisplayName("CreateTask saves and returns created Task")
  void createTaskSavesAndReturnsCreatedTask() {
    TaskToCreate toCreate = mock(TaskToCreate.class);
    Task task = mock(Task.class);
    when(toCreate.create()).thenReturn(task);

    Task result = service.createTask(toCreate);

    verify(taskRepository).save(task);
    assertThat(result).isEqualTo(task);
  }

  @Test
  @DisplayName("CreateTask throws exception when TaskToCreate is null")
  void createTaskThrowsExceptionWhenTaskToCreateIsNull() {
    assertThatThrownBy(() -> service.createTask(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("taskToCreate");
  }

  @Test
  @DisplayName("FindTask returns Task when found")
  void findTaskReturnsTaskWhenFound() {
    TaskId id = mock(TaskId.class);
    Task task = mock(Task.class);
    when(taskRepository.findById(id)).thenReturn(Optional.of(task));

    Optional<Task> result = service.findTask(id);

    assertThat(result).contains(task);
  }

  @Test
  @DisplayName("FindTask returns empty when not found")
  void findTaskReturnsEmptyWhenNotFound() {
    TaskId id = mock(TaskId.class);
    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    Optional<Task> result = service.findTask(id);

    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("FindTask throws exception when id is null")
  void findTaskThrowsExceptionWhenIdIsNull() {
    assertThatThrownBy(() -> service.findTask(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("id");
  }

  @Test
  @DisplayName("CompleteTask marks task as completed and saves it")
  void completeTaskMarksTaskAsCompletedAndSavesIt() {
    TaskId id = mock(TaskId.class);
    Task task = mock(Task.class);
    when(taskRepository.findById(id)).thenReturn(Optional.of(task));

    service.completeTask(id);

    verify(task).markAsCompleted();
    verify(taskRepository).save(task);
  }

  @Test
  @DisplayName("CompleteTask does nothing if task not found")
  void completeTaskDoesNothingIfTaskNotFound() {
    TaskId id = mock(TaskId.class);
    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    service.completeTask(id);

    verify(taskRepository, never()).save(any());
  }

  @Test
  @DisplayName("CompleteTask throws exception when taskId is null")
  void completeTaskThrowsExceptionWhenTaskIdIsNull() {
    assertThatThrownBy(() -> service.completeTask(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("taskId");
  }

  @Test
  @DisplayName("DeleteTask deletes task by id")
  void deleteTaskDeletesTaskById() {
    TaskId id = mock(TaskId.class);

    service.deleteTask(id);

    verify(taskRepository).delete(id);
  }

  @Test
  @DisplayName("DeleteTask throws exception when taskId is null")
  void deleteTaskThrowsExceptionWhenTaskIdIsNull() {
    assertThatThrownBy(() -> service.deleteTask(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("taskId");
  }

  @Test
  @DisplayName("UpdateTask saves the given task")
  void updateTaskSavesTheGivenTask() {
    Task task = mock(Task.class);

    service.updateTask(task);

    verify(taskRepository).save(task);
  }

  @Test
  @DisplayName("UpdateTask throws exception when task is null")
  void updateTaskThrowsExceptionWhenTaskIsNull() {
    assertThatThrownBy(() -> service.updateTask(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("task");
  }

  @Test
  @DisplayName("FindTasks returns tasks for given userId")
  void findTasksReturnsTasksForGivenUserId() {
    UserId userId = mock(UserId.class);
    Tasks tasks = mock(Tasks.class);
    when(taskRepository.findAllByUserId(userId)).thenReturn(tasks);

    Tasks result = service.findTasks(userId);

    assertThat(result).isEqualTo(tasks);
  }

  @Test
  @DisplayName("FindTasks throws exception when userId is null")
  void findTasksThrowsExceptionWhenUserIdIsNull() {
    assertThatThrownBy(() -> service.findTasks(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("userId");
  }

  @Test
  @DisplayName("FindTaskById returns Task when found")
  void findTaskByIdReturnsTaskWhenFound() {
    TaskId id = mock(TaskId.class);
    Task task = mock(Task.class);
    when(taskRepository.findById(id)).thenReturn(Optional.of(task));

    Optional<Task> result = service.findTaskById(id);

    assertThat(result).contains(task);
  }

  @Test
  @DisplayName("FindTaskById returns empty when not found")
  void findTaskByIdReturnsEmptyWhenNotFound() {
    TaskId id = mock(TaskId.class);
    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    Optional<Task> result = service.findTaskById(id);

    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("FindTaskById throws exception when taskId is null")
  void findTaskByIdThrowsExceptionWhenTaskIdIsNull() {
    assertThatThrownBy(() -> service.findTaskById(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("taskId");
  }
}
