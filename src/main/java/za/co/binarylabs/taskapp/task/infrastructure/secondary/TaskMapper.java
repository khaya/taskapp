package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.springframework.stereotype.Component;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.task.domain.*;

@Component
public class TaskMapper {

  public Task toDomain(JpaTask entity) {
    Assert.notNull("entity", entity);
    return Task.builder()
        .id(TaskId.fromString(entity.getId()))
        .userId(UserId.fromString(entity.getUserId()))
        .title(new TaskTitle(entity.getTitle()))
        .description(new TaskDescription(entity.getDescription()))
        .priority(entity.getPriority())
        .status(entity.getStatus())
        .build();
  }

  public JpaTask toJpaEntity(Task task) {
    Assert.notNull("task", task);
    JpaTask entity = new JpaTask();
    entity.setId(task.id().value());
    entity.setUserId(task.userId().value()); // Uncomment if userId exists
    entity.setTitle(task.title().get());
    entity.setDescription(task.description().get());
    entity.setPriority(task.priority().name());
    entity.setStatus(task.status().name());
    return entity;
  }
}
