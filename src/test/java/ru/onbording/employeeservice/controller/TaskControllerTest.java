package ru.onbording.employeeservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.TestUtils;
import ru.onbording.employeeservice.data.TaskData;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.service.TaskService;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
public class TaskControllerTest extends InitializerTest {
    private static final String URL = "/api/task";

    @Autowired
    private MockMvc mvc;
    //todo те же замечания, что и в EmployeeControllerTest //done

    @Autowired
    private TaskService taskService;

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchTaskById() throws Exception {
        String uuid = "631e0556-8f3a-4e58-9039-4e61fcba217a";
        TaskDto taskDto = taskService.fetchTaskDtoById(uuid);
        mvc.perform(get(URL + "/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(taskDto))); //done
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testDeleteTaskById() throws Exception {
        String uuid = "4d30bb53-8d3a-4253-978f-40e2a7b5a7b0";
        mvc.perform(delete(URL + "/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.message").value(String.format("Запись удалена '%s'", uuid))); //done
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchAllTask() throws Exception {
        List<TaskDto> taskDtoList = taskService.fetchTaskAll();
        mvc.perform(get(URL + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(taskDtoList))); //done;
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchTaskByEmployeeId() throws Exception {
        int employeeId = 4;
        List<TaskDto> taskDtoList = taskService.fetchTaskByEmployeeId(Integer.toString(employeeId));
        mvc.perform(get(URL + "/employee/{id}", employeeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(taskDtoList))); //done;
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testCreateTask() throws Exception {
        String uuid = UUID.randomUUID().toString();
        //todo тут не придумал как сравнить ответ
        mvc.perform(post(URL + "/")
                        .content(TestUtils.asJsonString(TaskData.createTaskDtoToInsert(uuid)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskDto.description").value("task_1_employee_4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskDto.employeeId").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].message")
                        .value("Задача добавлена работнику '4'"));//done
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testUpdateTask() throws Exception {
        String uuid = "b30cb4db-8595-43f8-99f3-c0544030300e";
        mvc.perform(put(URL + "")
                        .content(TestUtils.asJsonString(TaskData.createTaskDtoToUpdate(uuid)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Задача '" + uuid + "' обновлена"));//done
    }
}
