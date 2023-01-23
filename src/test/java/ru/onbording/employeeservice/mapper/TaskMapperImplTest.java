package ru.onbording.employeeservice.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.mapper.Impl.TaskMapperImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TaskMapperImpl.class)
public class TaskMapperImplTest {

    @Autowired
    private TaskMapperImpl taskMapper;

    @Test
    void testMapperTaskEntityToDto() { //todo результат сравни с заранее подготовленной сущностью
        UUID uuid = UUID.randomUUID();
        Task entity = Task.builder()
                .uuid(uuid)
                .description("Test")
                .employeeId(12L)
                .build();
        TaskDto dto = taskMapper.entityToDto(entity);
        assertThat(dto)
                .hasFieldOrPropertyWithValue("description", "Test")
                .hasFieldOrPropertyWithValue("employeeId", "12")
                .hasFieldOrPropertyWithValue("uuid", uuid.toString());
    }

    @Test
    void testMapperTaskDtoToEntity() { //todo результат сравни с заранее подготовленной сущностью
        TaskDto dto = TaskDto.builder()
                .description("Test")
                .employeeId("1")
                .build();
        Task entity = taskMapper.dtoToEntity(dto);
        assertThat(entity)
                .hasFieldOrPropertyWithValue("description", "Test")
                .hasFieldOrPropertyWithValue("employeeId", 1L);
    }
}
