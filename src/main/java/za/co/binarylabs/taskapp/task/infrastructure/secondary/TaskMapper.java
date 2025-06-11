package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.springframework.stereotype.Component;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.*;

@Component
public class TaskMapper {

  private final SpringDataJpaUserRepository userRepository;

  public TaskMapper(SpringDataJpaUserRepository userRepository) {
    Assert.notNull("userRepository", userRepository);
    this.userRepository = userRepository;
  }

  public Task toDomain(JpaTask entity) {
    Assert.notNull("entity", entity);
    return Task.builder()
        .id(TaskId.fromString(entity.getId()))
        .user(new User(UserId.fromString(entity.getUser().getId()), entity.getUser().getUsername()))
        .title(new TaskTitle(entity.getTitle()))
        .description(new TaskDescription(entity.getDescription()))
        .priority(entity.getPriority())
        .status(entity.getStatus())
        .build();
  }

  //TODO fix this
  public JpaTask toJpaEntity(Task task) {
    Assert.notNull("task", task);
    JpaTask entity = new JpaTask();
    entity.setId(task.id().value());
    entity.setUser(new JpaUser(task.user().userId().value(),task.user().username()));
    entity.setTitle(task.title().get());
    entity.setDescription(task.description().get());
    entity.setPriority(task.priority().name());
    entity.setStatus(task.status().name());
    return entity;
  }
}
