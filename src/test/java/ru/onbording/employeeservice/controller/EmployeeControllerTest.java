package ru.onbording.employeeservice.controller;

//todo ctrl+O ?
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.TestUtils;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.service.EmployeeService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends InitializerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private EmployeeService employeeService;

    private static final String URL = "/api/employee";

    @Test
    void testFetchEmployeeById() throws Exception {
        Long id = 3L;
        EmployeeDto employeeDto = employeeService.fetchEmployeeDtoById(id);
        mvc.perform(get(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(employeeDto)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)); //todo стоит проверять не только id, а всю сущность EmployeeDto
    }

    @Test
    void testDeleteEmployee() throws Exception {
        int id = 1;
        mvc.perform(delete(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(String.format("Запись удалена '%s'", id)));
    }

    @Test
    void test0AllEmployee() throws Exception {
        List<EmployeeDto> employeeDtoList = employeeService.fetchEmployeeAll();
        mvc.perform(get(URL + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(employeeDtoList)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty()); //todo почему бы не сравнить с заранее подготовленным List<EmployeeDto>
    }

    @Test
    void testCreateEmployee() throws Exception {
        mvc.perform(post(URL + "/")
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoToInsert()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeDto.id").exists()) //todo так же можно сравнить весь EmployeeDto, а чтоб исключить рандом в UUID передать его при отправке
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message")
                        .value("Запись добавлена '7'"));//todo наверное ты ожидаешь определённый ответ, а не просто не пустой
    }

    @Test
    void testCreateListEmployee() throws Exception {
        mvc.perform(post(URL + "/list")
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoListToInsert()))
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
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoToUpdate()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeDto.id").exists()) //todo сравни с заранее подготовленной сущностью
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message").isNotEmpty()); //todo наверное ты ожидаешь определённый ответ, а не просто не пустой
    }
}
