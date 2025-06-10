package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.*;

import java.util.Optional;

@Repository
public class JpaTaskRepository implements TaskRepository {

  private final SpringDataJpaTaskRepository repository;
  private final TaskMapper taskMapper;

  public JpaTaskRepository(SpringDataJpaTaskRepository repository, TaskMapper taskMapper) {
    Assert.notNull("repository", repository);
    Assert.notNull("taskMapper", taskMapper);
    this.repository = repository;
    this.taskMapper = taskMapper;
  }

  @Override
  public Optional<Task> findById(TaskId taskId) {
    return repository.findById(taskId.value())
        .map(taskMapper::toDomain);
  }

  @Override
  public void save(Task task) {
    repository.save(taskMapper.toJpaEntity(task));
  }

  @Override
  public void delete(TaskId taskId) {
    repository.deleteById(taskId.value());
  }

  @Override
  public Tasks findAllByUserId(UserId userId) {
    var entities = repository.findAllByUserId(userId.value());
    var tasks = entities.stream().map(taskMapper::toDomain).toList();
    return new Tasks(tasks);
  }
}
