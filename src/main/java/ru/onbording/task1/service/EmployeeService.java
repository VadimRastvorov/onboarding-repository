//todo для работы с БД создай отдельный класс EmployeeService, так же обработай случай, если такого id нет в бд //done
package ru.onbording.task1.service;

import com.baeldung.springsoap.gen.*;

public interface EmployeeService {
    GetEmployeeResponse getEmployeeById(Long id);

    SetEmployeeResponse saveEmployee(SetEmployeeRequest setEmployeeRequest);

    GetEmployeesResponse getEmployeesAll();

    DeleteEmployeeResponse deleteEmployeeById(Long id);
}
