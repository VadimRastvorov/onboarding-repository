package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.data.TaskData;
import ru.onbording.employeeservice.dto.TaskDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskValidationServiceTest extends InitializerTest {
    @Autowired
    TaskValidationService taskValidationService;

    @Test
    void testCheckDataValidCheck() {
        String uuid = UUID.randomUUID().toString();
        List<String> messages = taskValidationService.checkData(TaskData.createTaskDtoToInsert(uuid));
        assertThat(messages.size()).isEqualTo(0);
    }

    @Test
    void testCheckDataWithOutEmployeeId() {
        TaskDto taskDto = TaskDto.builder()
                .uuid(UUID.randomUUID().toString())
                .build();
        assertThat(taskValidationService.checkData(taskDto))
                .isEqualTo(Collections.singletonList(MessageBundleConfig.getMessage("task.checkEmployeeId")));
    }

    @Test
    void testCheckDataNoValidEmployeeId() {
        String employeeId = "100";
        TaskDto taskDto = TaskDto.builder()
                .uuid(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .description("task_1_employee_100")
                .build();
        assertThat(taskValidationService.checkData(taskDto))
                .isEqualTo(Collections.singletonList(MessageBundleConfig.getMessage("task.checkEmployee", employeeId)));
    }

    @Test
    void testCheckDataMaxTaskEmployee() {
        List<String> messages = new ArrayList<>();
        String employeeId = "1";
        String position = "ASSISTANT";
        messages.add(MessageBundleConfig.getMessage("task.taskCount", employeeId, position));
        TaskDto taskDto = TaskDto.builder()
                .uuid(UUID.randomUUID().toString())
                .employeeId(employeeId)
                .description("task_1_employee_1")
                .build();
        assertThat(taskValidationService.checkData(taskDto)).isEqualTo(messages);
    }
}
