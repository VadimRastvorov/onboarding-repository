package ru.onbording.task1.service;

import ru.onbording.task1.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee fetchEmployeeById(Long id);

    List<String> saveEmployee(Employee employee);

    List<Employee> fetchEmployeesAll();

    String deleteEmployeeById(Long id);

    void deleteOneEmployee();
}
