package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.task.domain.Task;
import za.co.binarylabs.taskapp.task.domain.TaskId;
import za.co.binarylabs.taskapp.task.domain.Tasks;
import za.co.binarylabs.taskapp.task.domain.UserId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@UnitTest
@DisplayName("JpaTaskRepository")
class JpaTaskRepositoryTest {

  private final SpringDataJpaTaskRepository springRepository = mock(SpringDataJpaTaskRepository.class);
  private final TaskMapper taskMapper = mock(TaskMapper.class);

  @Test
  @DisplayName("Constructor throws exception when repository is null")
  void constructorThrowsExceptionWhenRepositoryIsNull() {
    assertThatThrownBy(() -> new JpaTaskRepository(null, taskMapper))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Constructor throws exception when taskMapper is null")
  void constructorThrowsExceptionWhenTaskMapperIsNull() {
    assertThatThrownBy(() -> new JpaTaskRepository(springRepository, null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("FindById throws and exception null TaskId is provided")
  void findByIdReturnsEmptyWhenNullTaskIdProvided() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    assertThatThrownBy(() -> repo.findById(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Delete throws exception when null TaskId is provided")
  void deleteThrowsExceptionWhenNullTaskIdProvided() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    assertThatThrownBy(() -> repo.delete(null))
      .isInstanceOf(NullPointerException.class)
      .hasMessageContaining("taskId");
  }

  @Test
  @DisplayName("Save throws exception when null Task is provided")
  void saveThrowsExceptionWhenNullTaskProvided() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    assertThatThrownBy(() -> repo.save(null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("FindAllByUserId throws exception when null UserId is provided")
  void findAllByUserIdThrowsExceptionWhenNullUserIdProvided() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    assertThatThrownBy(() -> repo.findAllByUserId(null))
      .isInstanceOf(NullPointerException.class)
      .hasMessageContaining("userId");
  }

  @Test
  @DisplayName("FindAllByUserId returns empty Tasks when repository returns empty list")
  void findAllByUserIdReturnsEmptyTasksWhenRepositoryReturnsEmptyList() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    UUID userIdValue = UUID.randomUUID();
    UserId userId = new UserId(userIdValue);

    when(springRepository.findAllByUserId(userIdValue.toString())).thenReturn(List.of());

    Tasks result = repo.findAllByUserId(userId);

    assertThat(result.stream()).isEmpty();
  }

  @Test
  @DisplayName("FindById returns empty when no task found")
  void findByIdReturnsEmptyWhenNoTaskFound() {
    JpaTaskRepository repo = new JpaTaskRepository(springRepository, taskMapper);
    UUID taskIdValue = UUID.randomUUID();
    when(springRepository.findById(taskIdValue.toString())).thenReturn(Optional.empty());

    Optional<Task> result = repo.findById(new TaskId(taskIdValue));
    assertThat(result).isEmpty();

    }
}
