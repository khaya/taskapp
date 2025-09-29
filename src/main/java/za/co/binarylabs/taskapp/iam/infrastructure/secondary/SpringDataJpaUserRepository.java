package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<JpaUser, String> {
    Optional<JpaUser> findById(String id);
    Optional<JpaUser> findByUsername(String username);
    Optional<JpaUser> findByEmail(String email);
}