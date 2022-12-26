package ru.onbording.task1.service.impl;

import com.baeldung.springsoap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.task1.model.EmployeeDto;
import ru.onbording.task1.repository.EmployeeRepository;
import ru.onbording.task1.service.EmployeeService;
import ru.onbording.task1.service.MappingService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository; //todo должен быть финальным //done

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private MappingService mappingService = new MappingServiceImpl();

    public GetEmployeeResponse getEmployeeById(Long id) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        if (employeeRepository.existsById(id)) {
            response.setEmployee(mappingService.convertEmployeeDtotoSoap(employeeRepository.findById(id).get()));
        }
        return response;
    }

    public SetEmployeeResponse saveEmployee(SetEmployeeRequest setEmployeeRequest) {
        SetEmployeeResponse response = new SetEmployeeResponse();
        EmployeeDto employee = mappingService.convertEmployeeSoapToDto(setEmployeeRequest.getEmployee());
        String employeeCheck = employee.employeeCheckData();
        if (employeeCheck != "") {
            response.setStatus("Создана запись с id: " + employee.getId());
        } else {
            employeeRepository.save(mappingService.convertEmployeeSoapToDto(setEmployeeRequest.getEmployee()));
            response.setStatus("Создана запись с id: " + employee.getId());
        }
        return response;
    }

    public GetEmployeesResponse getEmployeesAll() {
        GetEmployeesResponse response = new GetEmployeesResponse();
        for (EmployeeDto employee : employeeRepository.findAll()) {
            response.getEmployee().add(mappingService.convertEmployeeDtotoSoap(employee));
        }
        return response;
    }

    public DeleteEmployeeResponse deleteEmployeeById(Long id) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        if (!employeeRepository.existsById(id)) {
            response.setStatus("Нет записи для удаления");
        } else {
            employeeRepository.deleteById(id);
            response.setStatus("Delete Row {" + id + "}");
        }
        return response;
    }
}
