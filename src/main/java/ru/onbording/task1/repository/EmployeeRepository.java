package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.onbording.task1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "delete FROM public.employees emp where emp.id = (select min(employees.id) from public.employees employees)",
            nativeQuery = true)
    void deleteEmployee();

    @Modifying
    @Query(value = "delete FROM public.employees emp where emp.id = ?",
            nativeQuery = true)
    void deleteEmployeeById(Long id);
}
