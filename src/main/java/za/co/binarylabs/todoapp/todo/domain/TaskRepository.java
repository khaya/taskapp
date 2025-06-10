package za.co.binarylabs.todoapp.todo.domain;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId taskId);
    void save(Task task);
    void delete(TaskId taskId);
    Tasks findAllByUserId(UserId userId);

}
