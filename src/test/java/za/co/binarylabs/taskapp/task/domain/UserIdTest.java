package za.co.binarylabs.taskapp.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("UserIdTest")
class UserIdTest {

  @Test
  @DisplayName("Creates UserId with valid UUID and returns correct value")
  void createsUserIdWithValidUUIDAndReturnsCorrectValue() {
    UUID uuid = UUID.randomUUID();
    UserId userId = new UserId(uuid);
    assertThat(userId.get()).isEqualTo(uuid);
    assertThat(userId.value()).isEqualTo(uuid.toString());
  }

  @Test
  @DisplayName("Throws exception when creating UserId with null UUID")
  void throwsExceptionWhenCreatingUserIdWithNullUUID() {
    assertThatThrownBy(() -> new UserId(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("id");
  }

  @Test
  @DisplayName("newId returns UserId with non-null UUID")
  void newIdReturnsUserIdWithNonNullUUID() {
    UserId userId = UserId.newId();
    assertThat(userId.get()).isNotNull();
  }

  @Test
  @DisplayName("fromString creates UserId from valid UUID string")
  void fromStringCreatesUserIdFromValidUUIDString() {
    UUID uuid = UUID.randomUUID();
    UserId userId = UserId.fromString(uuid.toString());
    assertThat(userId.get()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("Throws exception when fromString is called with null")
  void throwsExceptionWhenFromStringIsCalledWithNull() {
    assertThatThrownBy(() -> UserId.fromString(null))
      .isInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("id");
  }

  @Test
  @DisplayName("Throws exception when fromString is called with invalid UUID string")
  void throwsExceptionWhenFromStringIsCalledWithInvalidUUIDString() {
    assertThatThrownBy(() -> UserId.fromString("not-a-uuid"))
      .isInstanceOf(IllegalArgumentException.class);
  }
}
