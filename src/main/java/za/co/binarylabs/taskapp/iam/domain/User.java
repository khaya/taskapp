package za.co.binarylabs.taskapp.iam.domain;

import za.co.binarylabs.taskapp.shared.error.domain.Assert;

public class User {

    private final UserId userId;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final boolean active;

    public User(UserId userId, String username, String email, String firstName, String lastName, boolean active) {
        Assert.notNull("userId", userId);
        Assert.field("username", username).notBlank();
        Assert.field("email", email).notBlank();
        Assert.field("firstName", firstName).notBlank();
        Assert.field("lastName", lastName).notBlank();
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public UserId userId() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public boolean isActive() {
        return active;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}