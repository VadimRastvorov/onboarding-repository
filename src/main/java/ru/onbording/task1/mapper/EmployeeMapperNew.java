package ru.onbording.task1.mapper;

import com.baeldung.springsoap.gen.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.onbording.task1.entity.Employee;

@Mapper //todo что это?
public interface EmployeeMapperNew { //todo зачем тебе интерфейс подходящий только для одного класса?
    EmployeeMapperNew INSTANCE = Mappers.getMapper(EmployeeMapperNew.class); //todo что это?

    //@Mapping(source = "lastName", target = "lastName") //todo что это?
    EmployeeDto EmployeeEntityToEmployeeDto(Employee employee);

    Employee EmployeeDtoToEmployeeEntity(EmployeeDto employee);
}
