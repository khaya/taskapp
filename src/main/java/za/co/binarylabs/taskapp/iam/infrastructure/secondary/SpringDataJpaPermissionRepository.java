package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaPermissionRepository extends JpaRepository<JpaPermission, String> {
    Optional<JpaPermission> findById(String id);
    Optional<JpaPermission> findByName(String name);
}