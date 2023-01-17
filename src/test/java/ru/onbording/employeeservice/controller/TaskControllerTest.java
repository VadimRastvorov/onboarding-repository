package ru.onbording.employeeservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.onbording.employeeservice.DatabaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TaskControllerTest extends DatabaseTest {
    private static final String URL = "/api/task";

    @Autowired
    private MockMvc mvc;

    @Test
    void testFetchTaskById() throws Exception {
        mvc.perform(get(URL + "/d4189f5f-2ec9-4097-98c9-f6186c26b5da"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTask() throws Exception {
        mvc.perform(delete(URL + "/d4189f5f-2ec9-4097-98c9-f6186c26b5da"))
                .andExpect(status().isOk());
    }

    @Test
    void testFetchAllTask() throws Exception {
        mvc.perform(get(URL + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testFetchTaskByEmployeeId() throws Exception {
        mvc.perform(get(URL + "/employee/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateTask() throws Exception {
        mvc.perform(post(URL + ""))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception {
        mvc.perform(put(URL + ""))
                .andExpect(status().isOk());
    }
}
