package za.co.binarylabs.taskapp.task.infrastructure.primary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.task.domain.Tasks;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@UnitTest
@DisplayName("RestTasksTest")
class RestTasksTest {

  @Test
  @DisplayName("Creates RestTasks with correct RestTask collection from Tasks")
  void fromCreatesRestTasksWithCorrectRestTaskCollectionFromTasks() {
    RestTask restTask1 = mock(RestTask.class);
    RestTask restTask2 = mock(RestTask.class);
    Tasks tasks = mock(Tasks.class);
    when(tasks.stream()).thenReturn(List.of(mock(za.co.binarylabs.taskapp.task.domain.Task.class), mock(za.co.binarylabs.taskapp.task.domain.Task.class)).stream());
    try (var mocked = mockStatic(RestTask.class)) {
      mocked.when(() -> RestTask.from(any())).thenReturn(restTask1, restTask2);
      RestTasks restTasks = RestTasks.from(tasks);
      assertThat(restTasks.getTasks()).containsExactly(restTask1, restTask2);
    }
  }

  @Test
  @DisplayName("Throws exception when Tasks is null")
  void fromThrowsExceptionWhenTasksIsNull() {
    assertThatThrownBy(() -> RestTasks.from(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("tasks");
  }

  @Test
  @DisplayName("Constructor throws exception when tasks collection is null")
  void constructorThrowsExceptionWhenTasksCollectionIsNull() {
    assertThatThrownBy(() -> {
      var constructor = RestTasks.class.getDeclaredConstructor(Collection.class);
      constructor.setAccessible(true);
      constructor.newInstance((Object) null);
    }).hasCauseInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("GetTasks returns the same collection passed to constructor")
  void getTasksReturnsTheSameCollectionPassedToConstructor() throws Exception {
    RestTask restTask = mock(RestTask.class);
    var constructor = RestTasks.class.getDeclaredConstructor(Collection.class);
    constructor.setAccessible(true);
    RestTasks restTasks = constructor.newInstance(List.of(restTask));
    assertThat(restTasks.getTasks()).containsExactly(restTask);
  }
}
