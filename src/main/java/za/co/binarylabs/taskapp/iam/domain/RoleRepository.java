package za.co.binarylabs.taskapp.iam.domain;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(RoleId roleId);
    Optional<Role> findByName(String name);
    void save(Role role);
    void delete(RoleId roleId);
}