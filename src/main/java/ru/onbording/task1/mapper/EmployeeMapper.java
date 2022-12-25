package ru.onbording.task1.mapper;

import com.baeldung.springsoap.gen.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.onbording.task1.model.EmployeeDto;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );
    //@Mapping(source = "lastName", target = "lastName")
    EmployeeDto EmployeeToEmployeeDto(Employee employee);
    Employee EmployeeDtoToEmployee(EmployeeDto employee);
}
