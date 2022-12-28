package ru.onbording.task1.mapper;

import com.baeldung.springsoap.gen.EmployeeDto;
import org.springframework.stereotype.Service;
import ru.onbording.task1.entity.Employee;

import java.time.LocalDate;

@Service
public class EmployeeMapper {
    public Employee employeeDtoToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .description(employeeDto.getDescription())
                .phone(employeeDto.getPhone())
                .birthday(stringTolocalDate(employeeDto.getBirthday()))
                .startDate(stringTolocalDate(employeeDto.getStartDate()))
                .endDate(stringTolocalDate(employeeDto.getEndDate()))
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .middleName(employeeDto.getMiddleName())
                .gender(employeeDto.getGender())
                .salary(Double.parseDouble(employeeDto.getSalary()))
                .position(employeeDto.getPosition())
                .build();
    }

    public EmployeeDto employeeEntityToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .phone(employee.getPhone())
                .position(employee.getPosition())
                .gender(employee.getGender())
                .birthday(localDateToString(employee.getBirthday()))
                .salary(employee.getSalary().toString())
                .startDate(localDateToString(employee.getStartDate()))
                .endDate(localDateToString(employee.getEndDate()))
                .description(employee.getDescription())
                .build();
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
