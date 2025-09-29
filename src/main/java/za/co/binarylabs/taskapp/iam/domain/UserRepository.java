package za.co.binarylabs.taskapp.iam.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId userId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void save(User user);
    void delete(UserId userId);
}