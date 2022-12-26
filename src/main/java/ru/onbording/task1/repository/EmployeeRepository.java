package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.onbording.task1.model.EmployeeDto;

public interface EmployeeRepository extends JpaRepository<EmployeeDto, Long> {
    //todo если возвращаешь список, тогда название метода findAllByGender // убрал вообще это
}
