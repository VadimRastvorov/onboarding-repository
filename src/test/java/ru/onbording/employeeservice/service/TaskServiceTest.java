package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.data.TaskData;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskServiceTest extends InitializerTest {
    @Autowired
    private TaskService taskService;

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchTaskDtoById() {
        String uuid = "631e0556-8f3a-4e58-9039-4e61fcba217a";
        assertThat(taskService.fetchTaskDtoById(uuid)).isEqualTo(TaskData.createTaskDtoById());
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchTaskByEmployeeId() {
        String employeeId = "6";
        assertThat(taskService.fetchTaskByEmployeeId(employeeId)).isEqualTo(TaskData.createTaskDtoListByEmployeeId());
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testSaveTask() {
        String uuid = UUID.randomUUID().toString();
        ResponseTaskMessagesDto responseTaskMessagesDto = taskService.saveTask(TaskData.createTaskDtoToInsert(uuid));
        assertThat(responseTaskMessagesDto)
                .isEqualTo(TaskData.createResponseTaskMessagesDtoInsert(responseTaskMessagesDto.getTaskDto().getUuid()));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testUpdateTask() {
        String uuid = "b30cb4db-8595-43f8-99f3-c0544030300e";
        assertThat(taskService.updateTask(TaskData.createTaskDtoToUpdate(uuid)))
                .isEqualTo(TaskData.createResponseMessageDtoUpdate(uuid));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees_small.sql", "/db/insert_tasks_small.sql"})
    void testFetchTaskAll() {
        assertThat(taskService.fetchTaskAll()).isEqualTo(TaskData.createTaskDtoListByAll());
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void deleteTaskById() {
        String uuid = "631e0556-8f3a-4e58-9039-4e61fcba217a";
        assertThat(taskService.deleteTaskById(uuid))
                .isEqualTo(TaskData.createResponseMessageDtoDelete(uuid));
    }
}
