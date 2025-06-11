package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("Tasks")
class TasksTest {

  @Test
  @DisplayName("Creates Tasks with non-empty collection and returns correct values")
  void createsTasksWithNonEmptyCollectionAndReturnsCorrectValues() {
    Task task = Task.builder().build();
    Tasks tasks = new Tasks(List.of(task));
    assertThat(tasks.get()).containsExactly(task);
    assertThat(tasks.isEmpty()).isFalse();
    assertThat(tasks.stream().count()).isEqualTo(1);
  }

  @Test
  @DisplayName("Creates Tasks with empty collection and isEmpty returns true")
  void createsTasksWithEmptyCollectionAndIsEmptyReturnsTrue() {
    Tasks tasks = new Tasks(Collections.emptyList());
    assertThat(tasks.get()).isEmpty();
    assertThat(tasks.isEmpty()).isTrue();
    assertThat(tasks.stream().count()).isZero();
  }

  @Test
  @DisplayName("Throws exception when creating Tasks with null collection")
  void throwsExceptionWhenCreatingTasksWithNullCollection() {
    assertThatThrownBy(() -> new Tasks(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("tasks");
  }
}
