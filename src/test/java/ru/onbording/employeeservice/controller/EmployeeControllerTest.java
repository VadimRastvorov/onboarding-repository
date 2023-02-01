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
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.service.EmployeeService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
public class EmployeeControllerTest extends InitializerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeService employeeService;

    private static final String URL = "/api/employee";

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})//todo над методами уже не нужно, ведь ты сделал над классом
    void testFetchEmployeeById() throws Exception {
        Long id = 3L;
        EmployeeDto employeeDto = employeeService.fetchEmployeeDtoById(id);
        mvc.perform(get(URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(employeeDto)));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
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
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void test0AllEmployee() throws Exception {
        List<EmployeeDto> employeeDtoList = employeeService.fetchEmployeeAll();
        mvc.perform(get(URL + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.asJsonString(employeeDtoList)));
    }

    @Test
    @Sql({"/db/delete_tables.sql"}) //todo тут надо, это правильно, потому что конфигурация отличается
    void testCreateEmployee() throws Exception {
        Long id = 1L;
        mvc.perform(post(URL + "/")
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoToInsert(id)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(TestUtils.asJsonString(EmployeeData.createResponseEmployeeMessagesDtoInsert(id))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[*].message")
                        .value("Запись добавлена '"+id+"'"));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testCreateListEmployee() throws Exception {
        mvc.perform(post(URL + "/list")
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoListToInsert()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(TestUtils.asJsonString(EmployeeData.createResponseEmployeeMessagesDtoInsertList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].messages[0].message")
                        .value("Запись добавлена '"+8+"'"));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testUpdateEmployee() throws Exception {
        Long id = 5L;
        mvc.perform(put(URL + "/")
                        .content(TestUtils.asJsonString(EmployeeData.createDataEmployeeDtoToUpdate(id)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeDto.id").exists())
                .andExpect(content()
                        .json(TestUtils.asJsonString(EmployeeData.createResponseEmployeeMessagesDtoUpdate(id))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].message")
                        .value("Запись обновлена '"+id+"'"));
    }
}
