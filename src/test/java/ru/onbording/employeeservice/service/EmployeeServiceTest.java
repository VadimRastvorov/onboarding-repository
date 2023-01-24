package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@AllArgsConstructor
public class EmployeeServiceTest extends DatabaseTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testFetchEmployeeALl() {
        List<EmployeeDto> employeeDtoList = employeeService.fetchEmployeeAll();
        assertThat(employeeDtoList).isNotNull();
    }

    @Test
    void testFetchEmployeeByIdWithValidId() {
        Employee employee = employeeService.fetchEmployeeById(employeeService.fetchEmployeeAll().get(0).getId());
        assertThat(employee).isNotNull();
    }

    @Test
    void testFetchEmployeeByIdWithOutValidId() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.fetchEmployeeById(119L));
    }

    @Test
    void testSaveEmployee() {
        ResponseEmployeeMessagesDto responseEmployeeMessagesDto =
                employeeService.saveEmployee(EmployeeData.createDataEmployeeDtoToInsert());
        assertThat(responseEmployeeMessagesDto.getMessages().get(0).getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("employee.addRow",
                        responseEmployeeMessagesDto.getEmployeeDto().getId()));
    }

    @Test
    void testUpdateEmployee() {
        EmployeeDto employeeDto = EmployeeData.createDataEmployeeDtoToUpdate();
        ResponseEmployeeMessagesDto responseEmployeeMessagesDto =
                employeeService.updateEmployee(employeeDto);
        assertThat(responseEmployeeMessagesDto.getMessages().get(0).getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("employee.updateRow", employeeDto.getId()));
    }

    @Test
    void testDeleteEmployeeByIdWithValidId() {
        Long employeeValidId = 3L;
        int countEmployee = employeeService.fetchEmployeeAll().size();
        ResponseMessageDto responseMessageDto = employeeService.deleteEmployeeById(employeeValidId);
        assertThat(responseMessageDto.getMessage())
                .isEqualTo(MessageBundleConfig.getMessage("employee.deleteRow", 3));
        assertThat(employeeService.fetchEmployeeAll().size()).isEqualTo(countEmployee - 1);
        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.fetchEmployeeById(employeeValidId));
    }

    @Test
    void testDeleteEmployeeByIdWithInvalidId() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.deleteEmployeeById(17L));
    }

    @Test
    void testDeleteOneRowEmployee() {
        int countEmployee = employeeService.fetchEmployeeAll().size();
        employeeService.deleteOneRowEmployee();
        assertThat(employeeService.fetchEmployeeAll().size()).isEqualTo(countEmployee - 1);
    }
}
