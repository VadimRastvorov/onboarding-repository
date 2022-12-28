package ru.onbording.task1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onbording.task1.entity.Employee;
import ru.onbording.task1.repository.EmployeeRepository;
import ru.onbording.task1.service.EmployeeService;
import ru.onbording.task1.service.EmployeeValidationService;

import java.util.List;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee fetchEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public List<String> saveEmployee(Employee employee) {
        EmployeeValidationService employeeValidationService = new EmployeeValidationServiceImpl();
        List<String> messages = employeeValidationService.checkEmployeeData(employee);
        if (messages.size() == 0) {
            employeeRepository.save(employee);
            messages.add("Запись добавленна ");
        }
        return messages;
    }

    public List<Employee> fetchEmployeesAll() {
        return employeeRepository.findAll();
    }

    public String deleteEmployeeById(Long id) {
        employeeRepository.deleteEmployeeById(id);
        return "Delete Row {" + id + "}";
    }

    public void deleteOneEmployee() {
        employeeRepository.deleteEmployee();
    }
}
