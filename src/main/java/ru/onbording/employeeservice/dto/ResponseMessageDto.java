package ru.onbording.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessageDto {
    private String message;
}
