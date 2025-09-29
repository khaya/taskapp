package za.co.binarylabs.taskapp.iam.infrastructure.secondary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;
import za.co.binarylabs.taskapp.iam.domain.*;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("UserMapper")
class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    @DisplayName("Converts domain User to JPA entity")
    void convertsDomainUserToJpaEntity() {
        User user = new User(
            UserId.newId(),
            "testuser",
            "test@example.com",
            "Test",
            "User",
            true
        );

        JpaUser jpaUser = mapper.toJpaEntity(user);

        assertThat(jpaUser.getId()).isEqualTo(user.userId().value());
        assertThat(jpaUser.getUsername()).isEqualTo(user.username());
        assertThat(jpaUser.getEmail()).isEqualTo(user.email());
        assertThat(jpaUser.getFirstName()).isEqualTo(user.firstName());
        assertThat(jpaUser.getLastName()).isEqualTo(user.lastName());
        assertThat(jpaUser.isActive()).isEqualTo(user.isActive());
    }

    @Test
    @DisplayName("Converts JPA entity to domain User")
    void convertsJpaEntityToDomainUser() {
        JpaUser jpaUser = new JpaUser(
            UserId.newId().value(),
            "testuser",
            "test@example.com",
            "Test",
            "User",
            true
        );

        User user = mapper.toDomain(jpaUser);

        assertThat(user.userId().value()).isEqualTo(jpaUser.getId());
        assertThat(user.username()).isEqualTo(jpaUser.getUsername());
        assertThat(user.email()).isEqualTo(jpaUser.getEmail());
        assertThat(user.firstName()).isEqualTo(jpaUser.getFirstName());
        assertThat(user.lastName()).isEqualTo(jpaUser.getLastName());
        assertThat(user.isActive()).isEqualTo(jpaUser.isActive());
    }

    @Test
    @DisplayName("Throws exception when converting null domain User")
    void throwsExceptionWhenConvertingNullDomainUser() {
        assertThatThrownBy(() -> mapper.toJpaEntity(null))
            .isInstanceOf(MissingMandatoryValueException.class);
    }

    @Test
    @DisplayName("Throws exception when converting null JPA entity")
    void throwsExceptionWhenConvertingNullJpaEntity() {
        assertThatThrownBy(() -> mapper.toDomain(null))
            .isInstanceOf(MissingMandatoryValueException.class);
    }
}