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

    private LocalDate stringTolocalDate(String date) { //todo ?????????????? ???????????????? ????????????
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date);
    }
}
