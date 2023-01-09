package ru.onbording.employeeservice.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmployeeMessagesDto {
    private EmployeeDto employeeDto;
    private List<ResponseMessageDto> messages;
}
