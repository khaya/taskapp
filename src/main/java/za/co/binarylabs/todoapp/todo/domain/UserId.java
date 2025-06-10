package za.co.binarylabs.todoapp.todo.domain;


import za.co.binarylabs.todoapp.shared.error.domain.Assert;

import java.util.UUID;

public record UserId(UUID id) {

    public UserId {
      Assert.notNull("id", id);
    }

    public static UserId newId() {
      return new UserId(UUID.randomUUID());
    }

    public UUID get() {
        return id;
    }

    @Override
    public String toString() {
        return "UserId{" + "id=" + id + '}';
    }
}
