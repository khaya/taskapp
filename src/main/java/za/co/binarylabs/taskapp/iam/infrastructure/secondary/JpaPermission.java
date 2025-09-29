package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import jakarta.persistence.*;

@Entity
@Table(name = "iam_permissions")
public class JpaPermission {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "action", nullable = false)
    private String action;

    public JpaPermission() {
        // Default constructor for JPA
    }

    public JpaPermission(String id, String name, String description, String resource, String action) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resource = resource;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}