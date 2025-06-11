package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.shared.error.domain.StringTooLongException;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("TaskDescription")
class TaskDescriptionTest {

  @Test
  @DisplayName("Creates TaskDescription with valid non-blank string")
  void createsTaskDescriptionWithValidNonBlankString() {
    String value = "A valid task description";
    TaskDescription description = new TaskDescription(value);
    assertThat(description.get()).isEqualTo(value);
  }

  @Test
  @DisplayName("Throws exception when creating TaskDescription with null")
  void throwsExceptionWhenCreatingTaskDescriptionWithNull() {
    assertThatThrownBy(() -> new TaskDescription(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating TaskDescription with blank string")
  void throwsExceptionWhenCreatingTaskDescriptionWithBlankString() {
    assertThatThrownBy(() -> new TaskDescription("   "))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating TaskDescription with string exceeding max length")
  void throwsExceptionWhenCreatingTaskDescriptionWithStringExceedingMaxLength() {
    String longString = "a".repeat(256);
    assertThatThrownBy(() -> new TaskDescription(longString))
      .isInstanceOf(StringTooLongException.class);
  }

  @Test
  @DisplayName("Allows TaskDescription with string at max length")
  void allowsTaskDescriptionWithStringAtMaxLength() {
    String maxLengthString = "a".repeat(255);
    TaskDescription description = new TaskDescription(maxLengthString);
    assertThat(description.get()).isEqualTo(maxLengthString);
  }
}
