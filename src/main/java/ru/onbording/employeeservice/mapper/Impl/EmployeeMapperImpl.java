package ru.onbording.employeeservice.mapper.Impl;

import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.mapper.Mapper;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class EmployeeMapperImpl implements Mapper<Employee, EmployeeDto> {

    @Override
    public Employee dtoToEntity(EmployeeDto dto) {
        return Employee.builder()
                .id(dto.getId())
                .birthday(toLocalDate(dto.getBirthday()))
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .endDate(toLocalDate(dto.getEndDate()))
                .startDate(toLocalDate(dto.getStartDate()))
                .salary(Double.parseDouble(dto.getSalary()))
                .position(dto.getPosition())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public EmployeeDto entityToDto(Employee entity) {
        return EmployeeDto.builder()
                .id(entity.getId())
                .birthday(localDateToString(entity.getBirthday()))
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .endDate(localDateToString(entity.getEndDate()))
                .startDate(localDateToString(entity.getStartDate()))
                .salary(entity.getSalary().toString())
                .position(entity.getPosition())
                .description(entity.getDescription())
                .build();
    }

    private LocalDate toLocalDate(String date) {
        if (Objects.isNull(date) || date.isBlank()) {
            return null;
        }
        return LocalDate.parse(date);
    }

    private String localDateToString(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.toString();
    }
}
