package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.iam.domain.*;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataJpaUserRepository repository;
    private final UserMapper userMapper;

    public JpaUserRepository(SpringDataJpaUserRepository repository, UserMapper userMapper) {
        Assert.notNull("repository", repository);
        Assert.notNull("userMapper", userMapper);
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        Assert.notNull("userId", userId);
        return repository.findById(userId.value())
            .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Assert.field("username", username).notBlank();
        return repository.findByUsername(username)
            .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Assert.field("email", email).notBlank();
        return repository.findByEmail(email)
            .map(userMapper::toDomain);
    }

    @Override
    public void save(User user) {
        Assert.notNull("user", user);
        JpaUser jpaUser = userMapper.toJpaEntity(user);
        repository.save(jpaUser);
    }

    @Override
    public void delete(UserId userId) {
        Assert.notNull("userId", userId);
        repository.deleteById(userId.value());
    }
}