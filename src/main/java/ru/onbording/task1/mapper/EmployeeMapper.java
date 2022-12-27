package ru.onbording.task1.mapper;

import com.baeldung.springsoap.gen.EmployeeDto;
import org.springframework.stereotype.Service;
import ru.onbording.task1.entity.Employee;

import java.time.LocalDate;

@Service
public class EmployeeMapper {
    public Employee employeeDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setLastName(employeeDto.getLastName());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setMiddleName(employeeDto.getMiddleName());
        employee.setPhone(employeeDto.getPhone());
        employee.setPosition(employeeDto.getPosition());
        employee.setGender(employeeDto.getGender());
        employee.setBirthday(stringTolocalDate(employeeDto.getBirthday()));
        employee.setSalary(Double.parseDouble(employeeDto.getSalary()));
        employee.setStartDate(stringTolocalDate(employeeDto.getStartDate()));
        employee.setEndDate(stringTolocalDate(employeeDto.getEndDate()));
        employee.setDescription(employee.getDescription());
        return employee;
    }

    public EmployeeDto employeeEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setGender(employee.getGender());
        employeeDto.setBirthday(localDateToString(employee.getBirthday()));
        employeeDto.setSalary(employee.getSalary().toString());
        employeeDto.setStartDate(localDateToString(employee.getStartDate()));
        employeeDto.setEndDate(localDateToString(employee.getEndDate()));
        employeeDto.setDescription(employee.getDescription());
        return employeeDto;
    }

    private String localDateToString(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.toString();
    }
    private LocalDate stringTolocalDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date);
    }
}
