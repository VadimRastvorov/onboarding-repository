package ru.onbording.task1.endpoint;
//todo название директории не соответствует содержимому //done

import com.baeldung.springsoap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.onbording.task1.entity.Employee;
import ru.onbording.task1.mapper.EmployeeMapper;
import ru.onbording.task1.service.EmployeeService;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private EmployeeMapper mappingService = new EmployeeMapper();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fetchEmployeeRequest")
    @ResponsePayload
    public FetchEmployeeResponse fetchEmployee(@RequestPayload FetchEmployeeRequest request) {
        FetchEmployeeResponse response = new FetchEmployeeResponse();
        response.setEmployee(mappingService.employeeEntityToDto(employeeService.fetchEmployeeById(request.getId())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        for (String message : employeeService.saveEmployee(mappingService.employeeDtoToEntity(request.getEmployee()))) {
            response.getStatus().add(message);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fetchEmployeesRequest")
    @ResponsePayload
    public FetchEmployeesResponse fetchEmployees(@RequestPayload FetchEmployeesRequest request) {
        FetchEmployeesResponse response = new FetchEmployeesResponse();
        for (Employee employee : employeeService.fetchEmployeesAll()) {
            response.getEmployee().add(mappingService.employeeEntityToDto(employee));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setStatus(employeeService.deleteEmployeeById(request.getId()));
        return response;
    }
}