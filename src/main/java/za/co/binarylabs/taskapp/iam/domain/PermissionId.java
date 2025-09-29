package za.co.binarylabs.taskapp.iam.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.UUID;

public record PermissionId(UUID id) {

    public PermissionId {
        Assert.notNull("id", id);
    }

    public static PermissionId newId() {
        return new PermissionId(UUID.randomUUID());
    }

    public static PermissionId fromString(String id) {
        Assert.notNull("id", id);
        return new PermissionId(UUID.fromString(id));
    }

    public UUID get() {
        return id;
    }

    public String value() {
        return id.toString();
    }
}