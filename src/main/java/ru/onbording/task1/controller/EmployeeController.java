package ru.onbording.task1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.onbording.task1.model.Dictionary;
import ru.onbording.task1.model.Employee;
import ru.onbording.task1.repository.DictionaryRepository;
import ru.onbording.task1.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController //todo в задаче 1 этапа нет RestController, убери
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private DictionaryRepository dictionaryRepository;

    @DeleteMapping("/{id}")
    public Optional<Employee> deleteEmployee(@PathVariable Long id)
    {
        Optional<Employee> employee = employeeRepository.findById(id);
        employeeRepository.delete(employee.get());
        return employee;
    }

    @GetMapping("/all")
    public List<Employee> allEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeId(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/gender/{gender}")
    public List<Employee> getEmployeeByGender(@PathVariable String gender) {
        return employeeRepository.findByGender(gender);
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("/dictionary")
    public Dictionary createDictionary (@RequestBody Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }
}