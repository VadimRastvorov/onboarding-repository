package ru.onbording.employeeservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaskDto { //todo наименование TaskDto
    //todo желательно у dto тип полей ставить String

    private UUID id; //todo наименования uuid

    private String description;

    private long employeeId;
}
