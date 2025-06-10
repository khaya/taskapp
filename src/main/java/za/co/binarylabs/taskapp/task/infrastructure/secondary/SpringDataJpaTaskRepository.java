package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaTaskRepository extends JpaRepository<JpaTask, String> {
  List<JpaTask> findAllByUserId(String id);
  Optional<JpaTask> findById(String id);
}
