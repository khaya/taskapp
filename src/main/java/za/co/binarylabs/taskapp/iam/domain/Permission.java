package za.co.binarylabs.taskapp.iam.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public class Permission {

    private final PermissionId permissionId;
    private final String name;
    private final String description;
    private final String resource;
    private final String action;

    public Permission(PermissionId permissionId, String name, String description, String resource, String action) {
        Assert.notNull("permissionId", permissionId);
        Assert.field("name", name).notBlank();
        Assert.field("description", description).notBlank();
        Assert.field("resource", resource).notBlank();
        Assert.field("action", action).notBlank();
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
        this.resource = resource;
        this.action = action;
    }

    public PermissionId permissionId() {
        return permissionId;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String resource() {
        return resource;
    }

    public String action() {
        return action;
    }
}