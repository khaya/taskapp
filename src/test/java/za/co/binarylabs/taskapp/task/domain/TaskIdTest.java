package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@UnitTest
class TaskIdTest {

  @Test
  @DisplayName("Creates TaskId with valid UUID")
  void createsTaskIdWithValidUUID() {
    UUID uuid = UUID.randomUUID();
    TaskId taskId = new TaskId(uuid);
    assertThat(taskId.get()).isEqualTo(uuid);
    assertThat(taskId.value()).isEqualTo(uuid.toString());
  }

  @Test
  @DisplayName("Throws exception when creating TaskId with null UUID")
  void throwsExceptionWhenCreatingTaskIdWithNullUUID() {
    assertThatThrownBy(() -> new TaskId(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Creates new TaskId with random UUID")
  void createsNewTaskIdWithRandomUUID() {
    TaskId taskId1 = TaskId.newId();
    TaskId taskId2 = TaskId.newId();
    assertThat(taskId1.get()).isNotNull();
    assertThat(taskId2.get()).isNotNull();
    assertThat(taskId1).isNotEqualTo(taskId2);
  }

  @Test
  @DisplayName("Creates TaskId from valid string")
  void createsTaskIdFromValidString() {
    UUID uuid = UUID.randomUUID();
    TaskId taskId = TaskId.fromString(uuid.toString());
    assertThat(taskId.get()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("Throws exception when creating TaskId from null string")
  void throwsExceptionWhenCreatingTaskIdFromNullString() {
    assertThatThrownBy(() -> TaskId.fromString(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating TaskId from invalid string")
  void throwsExceptionWhenCreatingTaskIdFromInvalidString() {
    assertThatThrownBy(() -> TaskId.fromString("not-a-uuid"))
      .isInstanceOf(IllegalArgumentException.class);
  }
}
