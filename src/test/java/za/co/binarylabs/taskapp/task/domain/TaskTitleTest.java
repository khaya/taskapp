package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.shared.error.domain.StringTooLongException;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("TaskTitleTest")
public class TaskTitleTest {

  @Test
  @DisplayName("Creates TaskTitle with valid non-blank string")
  void createsTaskTitleWithValidNonBlankString() {
    String value = "A valid task title";
    TaskTitle title = new TaskTitle(value);
    assertThat(title.get()).isEqualTo(value);
  }

  @Test
  @DisplayName("Throws exception when creating TaskTitle with null")
  void throwsExceptionWhenCreatingTaskTitleWithNull() {
    assertThatThrownBy(() -> new TaskTitle(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating TaskTitle with blank string")
  void throwsExceptionWhenCreatingTaskTitleWithBlankString() {
    assertThatThrownBy(() -> new TaskTitle("   "))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating TaskTitle with string exceeding max length")
  void throwsExceptionWhenCreatingTaskTitleWithStringExceedingMaxLength() {
    String longString = "a".repeat(256);
    assertThatThrownBy(() -> new TaskTitle(longString))
      .isInstanceOf(StringTooLongException.class);
  }

  @Test
  @DisplayName("Allows TaskTitle with string at max length")
  void allowsTaskTitleWithStringAtMaxLength() {
    String maxLengthString = "a".repeat(100);
    TaskTitle title = new TaskTitle(maxLengthString);
    assertThat(title.get()).isEqualTo(maxLengthString);
  }

  @Test
  @DisplayName("Creates TaskTitle with string containing special characters")
  void createsTaskTitleWithStringContainingSpecialCharacters() {
    String value = "!@#$%^&*()_+-=,./<>?;:'\"[]{}|`~";
    TaskTitle title = new TaskTitle(value);
    assertThat(title.get()).isEqualTo(value);
  }

  @Test
  @DisplayName("Throws exception when creating TaskTitle with empty string")
  void throwsExceptionWhenCreatingTaskTitleWithEmptyString() {
    assertThatThrownBy(() -> new TaskTitle(""))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Creates TaskTitle with string containing leading and trailing spaces")
  void createsTaskTitleWithStringContainingLeadingAndTrailingSpaces() {
    String value = "   Task with spaces   ";
    TaskTitle title = new TaskTitle(value);
    assertThat(title.get()).isEqualTo(value);
  }
}
