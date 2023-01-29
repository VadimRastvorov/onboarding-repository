package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
public class EmployeeServiceTest extends InitializerTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees_small.sql"})
    void testFetchEmployeeALl() { //todo результат сравни с заранее подготовленным списком //done
        assertThat(employeeService.fetchEmployeeAll()).isEqualTo(EmployeeData.createDataEmployeeDtoList());
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchEmployeeByIdWithValidId() { //todo результат сравни с заранее подготовленной сущностью //done
        assertThat(employeeService.fetchEmployeeById(5L)).isEqualTo(EmployeeData.createDataEmployeeById());
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testFetchEmployeeByIdWithOutValidId() {
        assertThrows(ResourceNotFoundException.class, () -> employeeService.fetchEmployeeById(119L));
    }

    @Test
    @Sql({"/db/delete_tables.sql"})
    void testSaveEmployee() { //todo дополнительно результат сравни с заранее подготовленной сущностью //done
        Long id = 1L;
        assertThat(employeeService.saveEmployee(EmployeeData.createDataEmployeeDtoToInsert(id))).isEqualTo(EmployeeData.createResponseEmployeeMessagesDtoInsert(id));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testUpdateEmployee() { //todo дополнительно результат сравни с заранее подготовленной сущностью //done
        Long id = 5L;
        assertThat(employeeService.updateEmployee(EmployeeData.createDataEmployeeDtoToUpdate(id))).isEqualTo(EmployeeData.createResponseEmployeeMessagesDtoUpdate(id));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testDeleteEmployeeByIdWithValidId() {
        Long employeeValidId = 3L;
        int countEmployee = employeeService.fetchEmployeeAll().size();
        assertThat(employeeService.deleteEmployeeById(employeeValidId)).isEqualTo(EmployeeData.createResponseMessagesDtoDelete(employeeValidId));
        assertThat(employeeService.fetchEmployeeAll().size()).isEqualTo(countEmployee - 1);
        assertThrows(ResourceNotFoundException.class, () -> employeeService.fetchEmployeeById(employeeValidId));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testDeleteEmployeeByIdWithInvalidId() {
        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployeeById(17L));
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testDeleteOneRowEmployee() {
        int countEmployee = employeeService.fetchEmployeeAll().size();
        employeeService.deleteOneRowEmployee();
        assertThat(employeeService.fetchEmployeeAll().size()).isEqualTo(countEmployee - 1);
    }
}
