package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.onbording.task1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "delete FROM public.employees emp where emp.id = (select min(employees.id) from public.employees employees)", //todo разбей чтоб влезало в ограничение
            nativeQuery = true)
    void deleteEmployee();

    @Modifying
    @Query(value = "delete FROM public.employees emp where emp.id = ?", //todo SQL запросы обычно пишут в UpperCase, чтобы было видно где команда, а где переменная
            nativeQuery = true) //todo не нужный отступ
    void deleteEmployeeById(Long id);
}
