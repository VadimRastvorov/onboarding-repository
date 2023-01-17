package ru.onbording.employeeservice.dto; //todo директория dto не должна быть в контроллере, положи рядом //done

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto { //todo  желательно у dto тип полей ставить String, для id можно оставить long //done

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
