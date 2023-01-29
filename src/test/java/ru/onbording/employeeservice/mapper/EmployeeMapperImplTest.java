package ru.onbording.employeeservice.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.mapper.Impl.EmployeeMapperImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeMapperImpl.class)
public class EmployeeMapperImplTest {
    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @Test
    void testMapperEmployeeEntityToDto() { //todo результат сравни с заранее подготовленной сущностью //done
        assertThat(employeeMapper.entityToDto(EmployeeData.createDataEmployee()))
                .isEqualTo(EmployeeData.createDataEmployeeDto());
    }

    @Test
    void testMapperTaskDtoToEntity() { //todo результат сравни с заранее подготовленной сущностью //done
        assertThat(employeeMapper.dtoToEntity(EmployeeData.createDataEmployeeDto()))
                .isEqualTo(EmployeeData.createDataEmployee());
    }
}
