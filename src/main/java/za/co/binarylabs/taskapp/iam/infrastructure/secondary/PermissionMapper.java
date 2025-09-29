package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.springframework.stereotype.Component;
import za.co.binarylabs.taskapp.shared.error.domain.Assert;
import za.co.binarylabs.taskapp.iam.domain.*;

@Component
public class PermissionMapper {

    public Permission toDomain(JpaPermission entity) {
        Assert.notNull("entity", entity);
        return new Permission(
            PermissionId.fromString(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            entity.getResource(),
            entity.getAction()
        );
    }

    public JpaPermission toJpaEntity(Permission permission) {
        Assert.notNull("permission", permission);
        return new JpaPermission(
            permission.permissionId().value(),
            permission.name(),
            permission.description(),
            permission.resource(),
            permission.action()
        );
    }
}