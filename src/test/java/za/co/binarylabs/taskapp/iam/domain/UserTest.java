package za.co.binarylabs.taskapp.iam.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("User")
class UserTest {

    @Test
    @DisplayName("Creates User with valid fields and returns correct values")
    void createsUserWithValidFieldsAndReturnsCorrectValues() {
        UserId userId = UserId.newId();
        String username = "testuser";
        String email = "test@example.com";
        String firstName = "Test";
        String lastName = "User";
        boolean active = true;
        
        User user = new User(userId, username, email, firstName, lastName, active);
        
        assertThat(user.userId()).isEqualTo(userId);
        assertThat(user.username()).isEqualTo(username);
        assertThat(user.email()).isEqualTo(email);
        assertThat(user.firstName()).isEqualTo(firstName);
        assertThat(user.lastName()).isEqualTo(lastName);
        assertThat(user.isActive()).isEqualTo(active);
        assertThat(user.fullName()).isEqualTo("Test User");
    }

    @Test
    @DisplayName("Throws exception when creating User with null userId")
    void throwsExceptionWhenCreatingUserWithNullUserId() {
        assertThatThrownBy(() -> new User(null, "username", "email@test.com", "First", "Last", true))
            .isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Throws exception when creating User with blank username")
    void throwsExceptionWhenCreatingUserWithBlankUsername() {
        UserId userId = UserId.newId();
        assertThatThrownBy(() -> new User(userId, "", "email@test.com", "First", "Last", true))
            .isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Throws exception when creating User with blank email")
    void throwsExceptionWhenCreatingUserWithBlankEmail() {
        UserId userId = UserId.newId();
        assertThatThrownBy(() -> new User(userId, "username", "", "First", "Last", true))
            .isInstanceOf(MissingMandatoryValueException.class);
    }
}