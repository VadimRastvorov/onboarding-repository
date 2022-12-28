package ru.onbording.task1.service;

import ru.onbording.task1.entity.Employee;

import java.util.List;

public interface EmployeeValidationService {
    List<String> checkEmployeeData(Employee employee);
}
