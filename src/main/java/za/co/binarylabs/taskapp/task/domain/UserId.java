package za.co.binarylabs.taskapp.task.domain;


import za.co.binarylabs.taskapp.shared.error.domain.Assert;

import java.util.UUID;

public record UserId(UUID id) {

    public UserId {
      Assert.notNull("id", id);
    }

    public static UserId newId() {
      return new UserId(UUID.randomUUID());
    }

    public static UserId fromString(String id) {
        Assert.notNull("id", id);
        return new UserId(UUID.fromString(id));
    }
    public UUID get() {
        return id;
    }

     public String value() {
            return id.toString();
        }

}
