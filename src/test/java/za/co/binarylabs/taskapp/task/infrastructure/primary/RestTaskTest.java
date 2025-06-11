package za.co.binarylabs.taskapp.task.infrastructure.primary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.task.domain.Task;
import za.co.binarylabs.taskapp.task.domain.TaskDescription;
import za.co.binarylabs.taskapp.task.domain.TaskId;
import za.co.binarylabs.taskapp.task.domain.TaskTitle;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
@DisplayName("RestTaskTest")
class RestTaskTest {

  @Test
  @DisplayName("Creates RestTask with correct values from Task")
  void fromCreatesRestTaskWithCorrectValuesFromTask() {
    TaskId id = new TaskId(java.util.UUID.randomUUID());
    TaskTitle title = new TaskTitle("Title");
    TaskDescription description = new TaskDescription("Description");
    LocalDateTime dueDate = LocalDateTime.now();
    Task.Priority priority = Task.Priority.HIGH;
    Task.Status status = Task.Status.COMPLETED;

    Task task = mock(Task.class);
    when(task.id()).thenReturn(id);
    when(task.title()).thenReturn(title);
    when(task.description()).thenReturn(description);
    when(task.dueDate()).thenReturn(dueDate);
    when(task.priority()).thenReturn(priority);
    when(task.status()).thenReturn(status);

    RestTask restTask = RestTask.from(task);

    assertThat(restTask.getId()).isEqualTo(id.value());
    assertThat(restTask.getTitle()).isEqualTo(title.get());
    assertThat(restTask.getDescription()).isEqualTo(description.get());
    assertThat(restTask.getDueDate()).isEqualTo(dueDate);
    assertThat(restTask.getPriority()).isEqualTo(priority.name());
    assertThat(restTask.getStatus()).isEqualTo(status.name());
  }

  @Test
  @DisplayName("Throws exception when Task is null")
  void fromThrowsExceptionWhenTaskIsNull() {
    assertThatThrownBy(() -> RestTask.from(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("task");
  }

  @Test
  @DisplayName("RestTaskBuilder builds RestTask with all fields set")
  void restTaskBuilderBuildsRestTaskWithAllFieldsSet() {
    String id = "id";
    String title = "title";
    String description = "desc";
    LocalDateTime dueDate = LocalDateTime.now();
    String priority = "LOW";
    String status = "PENDING";

    RestTask restTask = new RestTask.RestTaskBuilder()
      .id(id)
      .title(title)
      .description(description)
      .dueDate(dueDate)
      .priority(priority)
      .status(status)
      .build();

    assertThat(restTask.getId()).isEqualTo(id);
    assertThat(restTask.getTitle()).isEqualTo(title);
    assertThat(restTask.getDescription()).isEqualTo(description);
    assertThat(restTask.getDueDate()).isEqualTo(dueDate);
    assertThat(restTask.getPriority()).isEqualTo(priority);
    assertThat(restTask.getStatus()).isEqualTo(status);
  }
}
