package ru.onbording.employeeservice.mapper.Impl;

import org.apache.logging.log4j.util.Strings;
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
                .birthday(getLocalDate(dto.getBirthday()))
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .endDate(getLocalDate(dto.getEndDate()))
                .startDate(getLocalDate(dto.getStartDate()))
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

    private LocalDate getLocalDate(String date) {
        if (Strings.isBlank(date)) {
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
