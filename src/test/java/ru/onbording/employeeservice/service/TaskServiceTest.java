package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.data.TaskData;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;
import ru.onbording.employeeservice.dto.TaskDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskServiceTest extends InitializerTest {
    @Autowired
    private TaskService taskService;

//todo те же замечания, что и в EmployeeServiceTest
    @Test
    void testFetchTaskDtoById() {
        TaskDto taskDto = taskService.fetchTaskDtoById(taskService.fetchTaskAll().get(0).getUuid());
        assertThat(taskDto).isNotNull();
    }

    @Test
    void testFetchTaskByEmployeeId() {
        List<TaskDto> taskDtoList = taskService.fetchTaskByEmployeeId(taskService.fetchTaskAll().get(0).getEmployeeId());
        assertThat(taskDtoList).isNotNull();
    }

    @Test
    void testSaveTask() {
        ResponseTaskMessagesDto responseTaskMessagesDto = taskService.saveTask(TaskData.createTaskDtoToInsert());
        assertThat(responseTaskMessagesDto.getMessages().get(0).getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("task.addRow",
                        responseTaskMessagesDto.getTaskDto().getUuid(), "4"));
    }

    @Test
    void testUpdateTask() {
        TaskDto taskDto = TaskData.createTaskDtoToUpdate();
        ResponseMessageDto responseMessageDto = taskService.updateTask(taskDto);
        assertThat(responseMessageDto.getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("task.updateRow",
                        taskDto.getUuid()));
    }

    @Test
    void testFetchTaskAll() {
        List<TaskDto> taskDtoList = taskService.fetchTaskAll();
        assertThat(taskDtoList).isNotNull();
    }

    @Test
    void deleteTaskById() {
        TaskDto taskDto = taskService.fetchTaskAll().get(0);
        ResponseMessageDto responseMessageDto = taskService.deleteTaskById(taskDto.getUuid());
        assertThat(responseMessageDto.getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("task.deleteRow",
                        taskDto.getUuid()));
    }
}
