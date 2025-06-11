package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaUserRepository extends JpaRepository<JpaUser, String> {
 }
