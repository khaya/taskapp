package za.co.binarylabs.taskapp.task.infrastructure.secondary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class JpaUser {

  @Id
  @Column(name="id")
  private String id;
  @Column(name="username")
  private String username;

  public JpaUser() {
    // Default constructor for JPA
  }
  public JpaUser(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
