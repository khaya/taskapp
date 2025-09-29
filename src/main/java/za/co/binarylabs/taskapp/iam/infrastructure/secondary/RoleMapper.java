package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.stereotype.Component;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.iam.domain.*;

@Component
public class RoleMapper {

    public Role toDomain(JpaRole entity) {
        Assert.notNull("entity", entity);
        return new Role(
            RoleId.fromString(entity.getId()),
            entity.getName(),
            entity.getDescription()
        );
    }

    public JpaRole toJpaEntity(Role role) {
        Assert.notNull("role", role);
        return new JpaRole(
            role.roleId().value(),
            role.name(),
            role.description()
        );
    }
}