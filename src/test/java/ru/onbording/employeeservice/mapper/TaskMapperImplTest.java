package ru.onbording.employeeservice.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.onbording.employeeservice.data.TaskData;
import ru.onbording.employeeservice.mapper.Impl.TaskMapperImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TaskMapperImpl.class)
public class TaskMapperImplTest {

    @Autowired
    private TaskMapperImpl taskMapper;

    @Test
    void testMapperTaskEntityToDto() {
        assertThat(taskMapper.entityToDto(TaskData.createTask()))
                .isEqualTo(TaskData.createTaskDto());
    }

    @Test
    void testMapperTaskDtoToEntity() {
        assertThat(taskMapper.dtoToEntity(TaskData.createTaskDto()))
                .isEqualTo(TaskData.createTask());
    }
}
