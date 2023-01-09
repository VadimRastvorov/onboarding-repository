package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.onbording.employeeservice.entity.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "DELETE FROM public.employees emp WHERE emp.id = " +
            "(SELECT MIN(employees.id) FROM public.employees employees)",
            nativeQuery = true)
    void deleteEmployee();

    @Modifying
    @Query(value = "DELETE FROM public.employees emp WHERE emp.id = :id",
            nativeQuery = true)
    void deleteEmployeeById(@Param("id") long id);

    @Query(value = "SELECT * FROM public.employees emp WHERE emp.id in (SELECT employee_id FROM public.task task " +
            "WHERE  task.id = :id)",
            nativeQuery = true)
    Employee findEmployeeByTaskId(@Param("id") UUID id); //todo не используется?

    List<Employee> findAllByOrderByIdAsc();
}
