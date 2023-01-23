package ru.onbording.employeeservice.controller;

//todo ctrl+O ?
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.data.EmployeeData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends DatabaseTest {
    private static final String URL = "/api/employee";

    @Autowired
    private MockMvc mvc;

    @Test
    void testFetchEmployeeById() throws Exception {
        int id = 3;
        mvc.perform(get(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)); //todo стоит проверять не только id, а всю сущность EmployeeDto
    }

    @Test
    void testDeleteEmployee() throws Exception {
        int id = 1;
        mvc.perform(delete(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Запись удалена " + id));
    }

    @Test
    void testAllEmployee() throws Exception {
        mvc.perform(get(URL + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty()); //todo почему бы не сравнить с заранее подготовленным List<EmployeeDto>
    }

    @Test
    void testCreateEmployee() throws Exception {
        mvc.perform(post(URL + "/")
                        .content(asJsonString(EmployeeData.createDataEmployeeDtoToInsert()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeDto.id").exists()) //todo так же можно сравнить весь EmployeeDto, а чтоб исключить рандом в UUID передать его при отправке
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message").isNotEmpty()); //todo наверное ты ожидаешь определённый ответ, а не просто не пустой
    }

    @Test
    void testCreateListEmployee() throws Exception {
        mvc.perform(post(URL + "/list")
                        .content(asJsonString(EmployeeData.createDataEmployeeDtoListToInsert()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].employeeDto").exists()) //todo сравни с заранее подготовленной сущностью
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].messages[*].message").isNotEmpty()); //todo наверное ты ожидаешь определённый ответ, а не просто не пустой
    }

    @Test
    void testUpdateEmployee() throws Exception {
        mvc.perform(put(URL + "/")
                        .content(asJsonString(EmployeeData.createDataEmployeeDtoToUpdate()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeDto.id").exists()) //todo сравни с заранее подготовленной сущностью
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message").isNotEmpty()); //todo наверное ты ожидаешь определённый ответ, а не просто не пустой
    }

    public static String asJsonString(final Object obj) { //todo такой метод нужно перенести в отдельный класс - TestUtils
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
