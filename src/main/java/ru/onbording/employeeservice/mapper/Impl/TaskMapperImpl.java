package ru.onbording.employeeservice.mapper.Impl;

import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.mapper.Mapper;

import java.util.Objects;
import java.util.UUID;

@Service
public class TaskMapperImpl implements Mapper<Task, TaskDto> {

    @Override
    public Task dtoToEntity(TaskDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return Task.builder()
                //.uuid(dto.getUuid() == null || dto.getUuid().isBlank() ?
                //        UUID.randomUUID() : UUID.fromString(dto.getUuid()))
                .uuid(UUID.fromString(dto.getUuid()))
                .description(dto.getDescription())
                .employeeId(stringToLong(dto.getEmployeeId()))
                .build();
    }

    @Override
    public TaskDto entityToDto(Task entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return TaskDto.builder()
                .uuid(entity.getUuid().toString())
                .employeeId(String.valueOf(entity.getEmployeeId()))
                .description(entity.getDescription())
                .build();
    }

    private Long stringToLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.parseLong(str);
    }
}
