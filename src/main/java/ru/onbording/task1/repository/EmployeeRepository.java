package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.onbording.task1.model.EmployeeDto;

public interface EmployeeRepository extends JpaRepository<EmployeeDto, Long> {

    @Query("")
    @Modifying
    void delete(@Param(value = "value") String value); //todo тебе шаблон
}
