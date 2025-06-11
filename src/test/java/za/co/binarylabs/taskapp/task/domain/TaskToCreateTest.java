package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("TaskToCreate")
class TaskToCreateTest {

  @Test
  @DisplayName("Creates TaskToCreate with valid values and create returns Task with correct fields")
  void createsTaskToCreateWithValidValuesAndCreateReturnsTaskWithCorrectFields() {
    String title = "Title";
    String description = "Description";
    LocalDate dueDate = LocalDate.now().plusDays(1);
    String priority = "HIGH";

    TaskToCreate toCreate = new TaskToCreate(title, description, dueDate, priority);
    Task task = toCreate.create();

    assertThat(task.title().get()).isEqualTo(title);
    assertThat(task.description().get()).isEqualTo(description);
    assertThat(task.dueDate()).isEqualTo(dueDate);
    assertThat(task.priority().name()).isEqualTo(priority);
    assertThat(task.status()).isEqualTo(Task.Status.OPEN);
  }

  @Test
  @DisplayName("Throws exception when creating TaskToCreate with null title")
  void throwsExceptionWhenCreatingTaskToCreateWithNullTitle() {
    assertThatThrownBy(() -> new TaskToCreate(null, "desc", LocalDate.now(), "LOW"))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("title");
  }

  @Test
  @DisplayName("Throws exception when creating TaskToCreate with null description")
  void throwsExceptionWhenCreatingTaskToCreateWithNullDescription() {
    assertThatThrownBy(() -> new TaskToCreate("title", null, LocalDate.now(), "LOW"))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("description");
  }

  @Test
  @DisplayName("Throws exception when creating TaskToCreate with null dueDate")
  void throwsExceptionWhenCreatingTaskToCreateWithNullDueDate() {
    assertThatThrownBy(() -> new TaskToCreate("title", "desc", null, "LOW"))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("dueDate");
  }

  @Test
  @DisplayName("Throws exception when creating TaskToCreate with null priority")
  void throwsExceptionWhenCreatingTaskToCreateWithNullPriority() {
    assertThatThrownBy(() -> new TaskToCreate("title", "desc", LocalDate.now(), null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("priority");
  }
}
