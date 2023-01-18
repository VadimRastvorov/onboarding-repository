package ru.onbording.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String gender;

    private String phone;

    private String position;

    private String salary;

    private String birthday;

    private String startDate;

    private String endDate;

    private String description;

}
