package ru.onbording.employeeservice.service;

import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.controller.dto.EmployeeDto;
import ru.onbording.employeeservice.controller.dto.EmployeeTaskDto;
import ru.onbording.employeeservice.controller.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.controller.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.entity.EmployeeTask;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MappingService {
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .birthday(employee.getBirthday())
                .gender(employee.getGender())
                .phone(employee.getPhone())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .endDate(employee.getEndDate())
                .startDate(employee.getStartDate())
                .salary(employee.getSalary())
                .position(employee.getPosition())
                .description(employee.getDescription())
                .employeeTaskDtoList(employee.getEmployeeTasks()
                        .stream()
                        .map(employeeTask -> {
                            return employeeTaskToEmployeeTaskDto(employeeTask);
                        })
                        .collect(Collectors.toList()))
                .build();
    }


    public Employee employeeDtoToEmployeeUpdate(EmployeeDto employeeDto, Employee employee) {
        if (Objects.nonNull(employeeDto.getBirthday())) {
            employee.setBirthday(employeeDto.getBirthday());
        }
        if (Objects.nonNull(employeeDto.getGender())) {
            employee.setGender(employeeDto.getGender());
        }
        if (Objects.nonNull(employeeDto.getPhone())) {
            employee.setPhone(employeeDto.getPhone());
        }
        if (Objects.nonNull(employeeDto.getLastName())) {
            employee.setLastName(employeeDto.getLastName());
        }
        if (Objects.nonNull(employeeDto.getFirstName())) {
            employee.setFirstName(employeeDto.getFirstName());
        }
        if (Objects.nonNull(employeeDto.getMiddleName())) {
            employee.setMiddleName(employeeDto.getMiddleName());
        }
        if (Objects.nonNull(employeeDto.getEndDate())) {
            employee.setEndDate(employeeDto.getEndDate());
        }
        if (Objects.nonNull(employeeDto.getStartDate())) {
            employee.setStartDate(employeeDto.getStartDate());
        }
        if (Objects.nonNull(employeeDto.getSalary())) {
            employee.setSalary(employeeDto.getSalary());
        }
        if (Objects.nonNull(employeeDto.getPosition())) {
            employee.setPosition(employeeDto.getPosition());
        }
        if (Objects.nonNull(employeeDto.getDescription())) {
            employee.setDescription(employeeDto.getDescription());
        }
        if (Objects.nonNull(employeeDto.getEmployeeTaskDtoList())) {
            for (EmployeeTaskDto employeeTaskDto : employeeDto.getEmployeeTaskDtoList()) {
                employee.getEmployeeTasks().add(EmployeeTask.builder()
                        .description(employeeTaskDto.getDescription())
                        .build());
            }
        }

        return employee;
    }

    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .birthday(employeeDto.getBirthday())
                .gender(employeeDto.getGender())
                .phone(employeeDto.getPhone())
                .lastName(employeeDto.getLastName())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .endDate(employeeDto.getEndDate())
                .startDate(employeeDto.getStartDate())
                .salary(employeeDto.getSalary())
                .position(employeeDto.getPosition())
                .description(employeeDto.getDescription())
                .employeeTasks(employeeDto.getEmployeeTaskDtoList()
                        .stream()
                        .map(employeeTaskDto -> {
                            return employeeTaskDtoToEmployeeTask(employeeTaskDto);
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    public EmployeeTaskDto employeeTaskToEmployeeTaskDto(EmployeeTask employeeTask) {
        if (Objects.isNull(employeeTask)) {
            return null;
        }
        return EmployeeTaskDto.builder()
                .id(employeeTask.getId())
                .employeeId(employeeTask.getEmployeeId())
                .description(employeeTask.getDescription())
                .build();
    }

    public EmployeeTask employeeTaskDtoToEmployeeTask(EmployeeTaskDto employeeTaskDto) {
        if (Objects.isNull(employeeTaskDto)) {
            return null;
        }
        return EmployeeTask.builder()
                .description(employeeTaskDto.getDescription())
                .employeeId(employeeTaskDto.getEmployeeId())
                .build();
    }

    public ResponseEmployeeMessagesDto listStringToResponseEmployeeMessagesDto(List<String> messages, EmployeeDto employeeDto) {
        return ResponseEmployeeMessagesDto.builder()
                .employeeDto(employeeDto)
                .messages(messages.stream().map(temp -> {
                    return ResponseMessageDto.builder().message(temp.toString()).build();
                }).collect(Collectors.toList()))
                .build();
    }
}
