package za.co.binarylabs.taskapp.iam.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.UUID;

public record RoleId(UUID id) {

    public RoleId {
        Assert.notNull("id", id);
    }

    public static RoleId newId() {
        return new RoleId(UUID.randomUUID());
    }

    public static RoleId fromString(String id) {
        Assert.notNull("id", id);
        return new RoleId(UUID.fromString(id));
    }

    public UUID get() {
        return id;
    }

    public String value() {
        return id.toString();
    }
}