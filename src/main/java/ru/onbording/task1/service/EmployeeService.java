package ru.onbording.task1.service;

import com.baeldung.springsoap.gen.*;

public interface EmployeeService {
    GetEmployeeResponse getEmployeeById(Long id);

    SetEmployeeResponse saveEmployee(SetEmployeeRequest setEmployeeRequest);

    GetEmployeesResponse getEmployeesAll();

    DeleteEmployeeResponse deleteEmployeeById(Long id);
}
