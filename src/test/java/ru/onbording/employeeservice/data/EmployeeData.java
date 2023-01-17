package ru.onbording.employeeservice.data;

import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.type.Gender;
import ru.onbording.employeeservice.type.Position;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeData {

    public static EmployeeDto createDataEmployeeDto()
    {
        return EmployeeDto.builder()
                .id(10L)
                .description("10L")
                .phone("90000000000")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("DIRECTOR")
                .salary("1000000")
                .gender("M")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().toString())
                .birthday(LocalDate.now().toString())
                .build();
    }
    public static EmployeeDto createDataEmployeeDtoToUpdate()
    {
        return EmployeeDto.builder()
                .id(5L)
                .description("5L")
                .phone("90000000000")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("DIRECTOR")
                .salary("900000")
                .gender("M")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().toString())
                .birthday(LocalDate.now().toString())
                .build();
    }
    public static EmployeeDto createDataEmployeeDtoToInsert()
    {
        return EmployeeDto.builder()
                .description("10L")
                .phone("90000000000")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("DIRECTOR")
                .salary("1000000")
                .gender("M")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().toString())
                .birthday(LocalDate.now().toString())
                .build();
    }

    public static Employee createDataEmployee()
    {
        return Employee.builder()
                .id(21L)
                .salary(200000D)
                .phone("77052222222")
                .endDate(LocalDate.now())
                .description("21L")
                .gender("M")
                .startDate(LocalDate.now())
                .position("MANAGER")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .birthday(LocalDate.now())
                .tasks(new ArrayList<>())
                .build();
    }

    public static Employee createDataEmployeeToUpdate()
    {
        return Employee.builder()
                .id(1L)
                .birthday(LocalDate.now())
                .gender(Gender.F.name())
                .phone("+78889991122")
                .lastName("Петрова")
                .firstName("Петра")
                .middleName("Петровна")
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .salary(4000000D)
                .position(Position.ACCOUNTANT.name())
                .description("ACCOUNTANT")
                .tasks(new ArrayList<>())
                .build();
    }

    public static Employee createDataEmployeeToInsert()
    {
        return Employee.builder()
                .birthday(LocalDate.now())
                .gender(Gender.F.name())
                .phone("78889991122")
                .lastName("Петрова")
                .firstName("Петра")
                .middleName("Петровна")
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .salary(4000000D)
                .position(Position.ACCOUNTANT.name())
                .description("ACCOUNTANT")
                .build();
    }
}
