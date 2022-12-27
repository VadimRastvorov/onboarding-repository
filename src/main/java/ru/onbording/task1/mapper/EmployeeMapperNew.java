package ru.onbording.task1.mapper;

import com.baeldung.springsoap.gen.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.onbording.task1.entity.Employee;

@Mapper
public interface EmployeeMapperNew {
    EmployeeMapperNew INSTANCE = Mappers.getMapper(EmployeeMapperNew.class);

    //@Mapping(source = "lastName", target = "lastName")
    EmployeeDto EmployeeEntityToEmployeeDto(Employee employee);

    Employee EmployeeDtoToEmployeeEntity(EmployeeDto employee);
}
