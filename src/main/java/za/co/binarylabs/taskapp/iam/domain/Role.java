package za.co.binarylabs.taskapp.iam.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public class Role {

    private final RoleId roleId;
    private final String name;
    private final String description;

    public Role(RoleId roleId, String name, String description) {
        Assert.notNull("roleId", roleId);
        Assert.field("name", name).notBlank();
        Assert.field("description", description).notBlank();
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    public RoleId roleId() {
        return roleId;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}