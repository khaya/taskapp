package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("Task")
class TaskTest {

  @Test
  @DisplayName("Builds Task with all fields set and returns correct values")
  void buildsTaskWithAllFieldsSetAndReturnsCorrectValues() {
    TaskId id = new TaskId(UUID.randomUUID());
    TaskTitle title = new TaskTitle("Title");
    TaskDescription description = new TaskDescription("Description");
    LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
    Task.Priority priority = Task.Priority.HIGH;
    Task.Status status = Task.Status.OPEN;
    UserId userId = new UserId(UUID.randomUUID());

    Task task = Task.builder()
      .id(id)
      .title(title)
      .description(description)
      .dueDate(dueDate)
      .priority(priority.name())
      .status(status.name())
      .userId(userId)
      .build();

    assertThat(task.id()).isEqualTo(id);
    assertThat(task.title()).isEqualTo(title);
    assertThat(task.description()).isEqualTo(description);
    assertThat(task.dueDate()).isEqualTo(dueDate);
    assertThat(task.priority()).isEqualTo(priority);
    assertThat(task.status()).isEqualTo(status);
    assertThat(task.userId()).isEqualTo(userId);
  }

  @Test
  @DisplayName("isOpen returns true when status is OPEN")
  void isOpenReturnsTrueWhenStatusIsOpen() {
    Task task = Task.builder()
      .status(Task.Status.OPEN.name())
      .build();
    assertThat(task.isOpen()).isTrue();
  }

  @Test
  @DisplayName("isOpen returns false when status is not OPEN")
  void isOpenReturnsFalseWhenStatusIsNotOpen() {
    Task task = Task.builder()
      .status(Task.Status.COMPLETED.name())
      .build();
    assertThat(task.isOpen()).isFalse();
  }

  @Test
  @DisplayName("isOverdue returns true when dueDate is before now")
  void isOverdueReturnsTrueWhenDueDateIsBeforeNow() {
    Task task = Task.builder()
      .dueDate(LocalDateTime.now().minusDays(1))
      .build();
    assertThat(task.isOverdue()).isTrue();
  }

  @Test
  @DisplayName("isOverdue returns false when dueDate is after now")
  void isOverdueReturnsFalseWhenDueDateIsAfterNow() {
    Task task = Task.builder()
      .dueDate(LocalDateTime.now().plusDays(1))
      .build();
    assertThat(task.isOverdue()).isFalse();
  }

  @Test
  @DisplayName("markAsCompleted sets status to COMPLETED")
  void markAsCompletedSetsStatusToCompleted() {
    Task task = Task.builder()
      .status(Task.Status.OPEN.name())
      .build();
    task.markAsCompleted();
    assertThat(task.status()).isEqualTo(Task.Status.COMPLETED);
  }

  @Test
  @DisplayName("Priority fromString returns correct enum for valid value")
  void priorityFromStringReturnsCorrectEnumForValidValue() {
    assertThat(Task.Priority.fromString("low")).isEqualTo(Task.Priority.LOW);
    assertThat(Task.Priority.fromString("MEDIUM")).isEqualTo(Task.Priority.MEDIUM);
    assertThat(Task.Priority.fromString("HIGH")).isEqualTo(Task.Priority.HIGH);
  }

  @Test
  @DisplayName("Priority fromString throws exception for null value")
  void priorityFromStringThrowsExceptionForNullValue() {
    assertThatThrownBy(() -> Task.Priority.fromString(null))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Priority fromString throws exception for invalid value")
  void priorityFromStringThrowsExceptionForInvalidValue() {
    assertThatThrownBy(() -> Task.Priority.fromString("urgent"))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Status fromString returns correct enum for valid value")
  void statusFromStringReturnsCorrectEnumForValidValue() {
    assertThat(Task.Status.fromString("open")).isEqualTo(Task.Status.OPEN);
    assertThat(Task.Status.fromString("IN_PROGRESS")).isEqualTo(Task.Status.IN_PROGRESS);
    assertThat(Task.Status.fromString("completed")).isEqualTo(Task.Status.COMPLETED);
  }

  @Test
  @DisplayName("Status fromString throws exception for null value")
  void statusFromStringThrowsExceptionForNullValue() {
    assertThatThrownBy(() -> Task.Status.fromString(null))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Status fromString throws exception for invalid value")
  void statusFromStringThrowsExceptionForInvalidValue() {
    assertThatThrownBy(() -> Task.Status.fromString("done"))
      .isInstanceOf(IllegalArgumentException.class);
  }
}
