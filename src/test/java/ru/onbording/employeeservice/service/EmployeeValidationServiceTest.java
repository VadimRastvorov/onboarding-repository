package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.data.EmployeeData;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class

EmployeeValidationServiceTest extends InitializerTest {
    @Autowired
    EmployeeValidationService employeeValidationService;

    @Test
    void testCheckDataValidCheck() {
        List<String> messages = employeeValidationService.checkData(EmployeeData.createDataEmployeeDto());
        assertThat(messages.size()).isEqualTo(0);
    }
}
