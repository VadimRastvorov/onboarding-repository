package ru.onbording.employeeservice.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.mapper.Impl.EmployeeMapperImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeMapperImpl.class)
public class EmployeeMapperImplTest {
    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @Test
    void testMapperEmployeeEntityToDto() { //todo результат сравни с заранее подготовленной сущностью
        Employee entity = EmployeeData.createDataEmployee();

        EmployeeDto dto = employeeMapper.entityToDto(entity);

        assertThat(dto)
                .hasFieldOrPropertyWithValue("phone", "77052222222")
                .hasFieldOrPropertyWithValue("salary", "200000.0")
                .hasFieldOrPropertyWithValue("id", 21L)
                .hasFieldOrPropertyWithValue("description", "21L")
                .hasFieldOrPropertyWithValue("gender", "M")
                .hasFieldOrPropertyWithValue("position", "MANAGER")
                .hasFieldOrPropertyWithValue("firstName", "firstName")
                .hasFieldOrPropertyWithValue("lastName", "lastName")
                .hasFieldOrPropertyWithValue("middleName", "middleName")
                .hasFieldOrPropertyWithValue("endDate", LocalDate.now().toString())
                .hasFieldOrPropertyWithValue("startDate", LocalDate.now().toString())
                .hasFieldOrPropertyWithValue("birthday", LocalDate.now().toString());
    }

    @Test
    void testMapperTaskDtoToEntity() { //todo результат сравни с заранее подготовленной сущностью
        EmployeeDto dto = EmployeeData.createDataEmployeeDto();

        Employee entity = employeeMapper.dtoToEntity(dto);

        assertThat(entity)
                .hasFieldOrPropertyWithValue("id", entity.getId())
                .hasFieldOrPropertyWithValue("description", "10L")
                .hasFieldOrPropertyWithValue("phone", "90000000000")
                .hasFieldOrPropertyWithValue("firstName", "firstName")
                .hasFieldOrPropertyWithValue("lastName", "lastName")
                .hasFieldOrPropertyWithValue("middleName", "middleName")
                .hasFieldOrPropertyWithValue("position", "DIRECTOR")
                .hasFieldOrPropertyWithValue("salary", Double.parseDouble("1000000"))
                .hasFieldOrPropertyWithValue("gender", "M")
                .hasFieldOrPropertyWithValue("endDate", LocalDate.now())
                .hasFieldOrPropertyWithValue("startDate", LocalDate.now())
                .hasFieldOrPropertyWithValue("birthday", LocalDate.now());
    }
}
