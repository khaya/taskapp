package za.co.binarylabs.taskapp.task.infrastructure.primary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.binarylabs.taskapp.UnitTest;
import za.co.binarylabs.taskapp.shared.error.domain.MissingMandatoryValueException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@UnitTest
@DisplayName("RestTaskToCreateTest")
class RestTaskToCreateTest {

  @Test
  @DisplayName("Builds RestTaskToCreate with all fields set")
  void buildsRestTaskToCreateWithAllFieldsSet() {
    String title = "Task title";
    String description = "Task description";
    LocalDate dueDate = LocalDate.now();
    String priority = "HIGH";

    RestTaskToCreate restTaskToCreate = new RestTaskToCreate(title, description, dueDate, priority);

    assertThat(restTaskToCreate.getTitle()).isEqualTo(title);
    assertThat(restTaskToCreate.getDescription()).isEqualTo(description);
    assertThat(restTaskToCreate.getDueDate()).isEqualTo(dueDate);
    assertThat(restTaskToCreate.getPriority()).isEqualTo(priority);
  }

  @Test
  @DisplayName("Throws an exception when mandatory fields are null")
  void buildsRestTaskToCreateWithOnlyMandatoryFields() {
    String title = "Task title";
    assertThatThrownBy(() -> new RestTaskToCreate(title, null, null, null))
      .isInstanceOf(MissingMandatoryValueException.class);
  }


}
