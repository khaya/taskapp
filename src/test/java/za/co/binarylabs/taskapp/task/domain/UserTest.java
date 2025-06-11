package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("User")
class UserTest {

  @Test
  @DisplayName("Creates User with valid UserId and username and returns correct values")
  void createsUserWithValidUserIdAndUsernameAndReturnsCorrectValues() {
    UserId userId = UserId.newId();
    String username = "testuser";
    User user = new User(userId, username);
    assertThat(user).extracting("userId").isEqualTo(userId);
    assertThat(user).extracting("username").isEqualTo(username);
  }

  @Test
  @DisplayName("Throws exception when creating User with empty username")
  void allowsCreatingUserWithEmptyUsername() {
    UserId userId = UserId.newId();
    assertThatThrownBy(() -> new User(userId, ""))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  @DisplayName("Throws exception when creating User with null username")
  void throwsExceptionWhenCreatingUserWithNullUsername() {
    UserId userId = UserId.newId();
    assertThatThrownBy(() -> new User(userId, null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }

}
