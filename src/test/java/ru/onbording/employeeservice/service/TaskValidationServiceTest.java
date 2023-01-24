package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.data.TaskData;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskValidationServiceTest extends DatabaseTest {
    @Autowired
    TaskValidationService taskValidationService;
    @Test
    void testCheckDataValidCheck()
    {
        List<String> messages = taskValidationService.checkData(TaskData.createTaskDtoToInsert());
        assertThat(messages.size()).isEqualTo(0);
    }
}
