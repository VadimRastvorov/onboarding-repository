package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.onbording.task1.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByGender(String gender);
}
