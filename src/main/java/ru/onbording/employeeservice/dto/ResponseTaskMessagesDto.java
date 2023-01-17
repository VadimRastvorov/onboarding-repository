package ru.onbording.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseTaskMessagesDto {
    private TaskDto taskDto;
    private List<ResponseMessageDto> messages;
}
