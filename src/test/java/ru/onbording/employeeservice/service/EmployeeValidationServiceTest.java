package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.repository.TaskRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmployeeValidationServiceTest extends DatabaseTest {
    @Autowired
    EmployeeValidationService employeeValidationService;

    @Test
    void testCheckDataValidCheck() {
        List<String> messages = employeeValidationService.checkData(EmployeeData.createDataEmployeeDto());
        assertThat(messages.size()).isEqualTo(0);
    }
}
