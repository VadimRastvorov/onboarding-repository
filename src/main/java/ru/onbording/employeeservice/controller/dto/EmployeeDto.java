package ru.onbording.employeeservice.controller.dto; //todo директория dto не должна быть в контроллере, положи рядом

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto { //todo  желательно у dto тип полей ставить String, для id можно оставить long

    private long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String gender;

    private String phone;

    private String position;

    private Double salary;

    private LocalDate birthday;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private List<EmployeeTaskDto> employeeTaskDtoList = new ArrayList<>();
}
