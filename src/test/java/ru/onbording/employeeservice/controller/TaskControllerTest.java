package ru.onbording.employeeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.data.TaskData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TaskControllerTest extends DatabaseTest {
    private static final String URL = "/api/task";

    @Autowired
    private MockMvc mvc;
//todo те же замечания, что и в EmployeeControllerTest
    @Test
    void testFetchTaskById() throws Exception {
        String uuid = "631e0556-8f3a-4e58-9039-4e61fcba217a";
        mvc.perform(get(URL + "/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(uuid));
    }

    @Test
    void testDeleteTaskById() throws Exception {
        String uuid = "4d30bb53-8d3a-4253-978f-40e2a7b5a7b0";
        mvc.perform(delete(URL + "/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Запись удалена " + uuid));
    }

    @Test
    void testFetchAllTask() throws Exception {
        mvc.perform(get(URL + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].uuid").isNotEmpty());
    }

    @Test
    void testFetchTaskByEmployeeId() throws Exception {
        int employeeId = 4;
        mvc.perform(get(URL + "/employee/{id}", employeeId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].uuid").isNotEmpty());
    }

    @Test
    void testCreateTask() throws Exception {
        mvc.perform(post(URL + "/")
                        .content(asJsonString(TaskData.createTaskDtoToInsert()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskDto.uuid").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message").isNotEmpty());
    }

    @Test
    void testUpdateTask() throws Exception {
        mvc.perform(put(URL + "")
                        .content(asJsonString(TaskData.createTaskDtoToUpdate()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isNotEmpty());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
