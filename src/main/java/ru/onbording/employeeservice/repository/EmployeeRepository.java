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
    @Query(value = "DELETE FROM PUBLIC.EMPLOYEES EMP WHERE EMP.ID = " +
            "(SELECT MIN(EMPLOYEES.ID) FROM PUBLIC.EMPLOYEES EMPLOYEES)", //todo разбей чтоб влезало в ограничение //done
            nativeQuery = true)
    void deleteEmployee();

    @Modifying
    @Query(value = "DELETE FROM PUBLIC.EMPLOYEES EMP WHERE EMP.ID = :id", //todo SQL запросы обычно пишут в UpperCase, чтобы было видно где команда, а где переменная //done
            nativeQuery = true)
    void deleteEmployeeById(@Param("id") long id);

    @Query(value = "SELECT * FROM PUBLIC.EMPLOYEES EMP WHERE EMP.ID in (SELECT employee_id FROM PUBLIC.TASK TASK " +
            "WHERE  TASK.ID = :id)",
            nativeQuery = true)
    Employee findEmployeeByTaskId(@Param("id") UUID id);

    List<Employee> findAllByOrderByIdAsc();
}
