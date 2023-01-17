package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.onbording.employeeservice.entity.Employee;

import java.util.List;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "DELETE FROM public.employees emp WHERE emp.id = " +
            "(SELECT MIN(employees.id) FROM public.employees employees)",
            nativeQuery = true)
    void deleteEmployee();

    List<Employee> findAllByOrderByIdAsc();
}
