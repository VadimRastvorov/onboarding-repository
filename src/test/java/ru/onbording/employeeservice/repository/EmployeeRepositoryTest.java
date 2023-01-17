package ru.onbording.employeeservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;
import ru.onbording.employeeservice.type.Gender;
import ru.onbording.employeeservice.type.Position;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest extends DatabaseTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testFetchEmployeeALl() {
        List<Employee> employees = employeeRepository.findAll();
        assertNotNull(employees);
        assertEquals(6, employees.size());
    }

    @Test
    void testFetchEmployeeByIdWithValidId() {
        Employee employeesList = employeeRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException(1L));
        assertEquals("Сидоров", employeesList.getLastName());
    }

    @Test
    void testFetchEmployeeByIdWithOuValidId() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> {
                    employeeRepository.findById(7L).orElseThrow(() -> new ResourceNotFoundException(7L));
                });
    }

    @Test
    void testInsertAndDeleteEmployee() {
        Long id = employeeRepository.save(EmployeeData.createDataEmployeeToInsert()).getId();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(id));
        assertEquals("Петрова", employee.getLastName());
        assertEquals(7, employeeRepository.findAll().size());

        employeeRepository.deleteById(id);
        assertThrows(
                ResourceNotFoundException.class,
                () -> {
                    employeeRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException(id));
                });
        assertEquals(6, employeeRepository.findAll().size());
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = employeeRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException(1L));
        assertEquals("Сидоров", employee.getLastName());
        assertEquals("+77777777777", employee.getPhone());

        Employee employeeSave = EmployeeData.createDataEmployeeToUpdate();
        employeeSave.getTasks().addAll(employee.getTasks());
        Long id = employeeRepository.save(employeeSave).getId();
        employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(id));
        assertEquals("Петрова", employee.getLastName());
        assertEquals("+78889991122", employee.getPhone());
    }
}
