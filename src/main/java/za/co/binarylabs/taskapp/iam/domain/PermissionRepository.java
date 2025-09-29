package za.co.binarylabs.taskapp.iam.domain;

import java.util.Optional;

public interface PermissionRepository {
    Optional<Permission> findById(PermissionId permissionId);
    Optional<Permission> findByName(String name);
    void save(Permission permission);
    void delete(PermissionId permissionId);
}