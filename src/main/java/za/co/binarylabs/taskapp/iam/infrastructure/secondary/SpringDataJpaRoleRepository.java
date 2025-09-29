package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRoleRepository extends JpaRepository<JpaRole, String> {
    Optional<JpaRole> findById(String id);
    Optional<JpaRole> findByName(String name);
}