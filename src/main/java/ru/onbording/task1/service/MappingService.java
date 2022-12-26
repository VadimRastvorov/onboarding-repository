package ru.onbording.task1.service;

import com.baeldung.springsoap.gen.Employee;
import ru.onbording.task1.model.EmployeeDto;

public interface MappingService {
    EmployeeDto convertEmployeeSoapToDto(Employee employee);

    Employee convertEmployeeDtotoSoap(EmployeeDto employeeDto);
}
