package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.stereotype.Component;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.iam.domain.*;

@Component
public class UserMapper {

    public User toDomain(JpaUser entity) {
        Assert.notNull("entity", entity);
        return new User(
            UserId.fromString(entity.getId()),
            entity.getUsername(),
            entity.getEmail(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.isActive()
        );
    }

    public JpaUser toJpaEntity(User user) {
        Assert.notNull("user", user);
        return new JpaUser(
            user.userId().value(),
            user.username(),
            user.email(),
            user.firstName(),
            user.lastName(),
            user.isActive()
        );
    }
}