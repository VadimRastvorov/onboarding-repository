package ru.onbording.employeeservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.onbording.employeeservice.DatabaseTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends DatabaseTest {
    private static final String URL = "/api/employee";

    @Autowired
    private MockMvc mvc;

    @Test
    void testFetchEmployeeById() throws Exception {
        mvc.perform(get(URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mvc.perform(delete(URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAllEmployee() throws Exception {
        mvc.perform(get(URL + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateEmployee() throws Exception {
        mvc.perform(post(URL + ""))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateListEmployee() throws Exception {
        mvc.perform(post(URL + "/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        mvc.perform(put(URL + ""))
                .andExpect(status().isOk());
    }
}
