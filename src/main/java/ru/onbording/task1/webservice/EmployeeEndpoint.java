package ru.onbording.task1.webservice;
//todo эндпоинты должны быть в endpoint

import com.baeldung.springsoap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.onbording.task1.service.EmployeeService;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) {
        return employeeService.getEmployeeById(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setEmployeeRequest")
    @ResponsePayload
    public SetEmployeeResponse setEmployee(@RequestPayload SetEmployeeRequest request) {
        return employeeService.saveEmployee(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse getEmployees(@RequestPayload GetEmployeesRequest request) {
        return employeeService.getEmployeesAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        return employeeService.deleteEmployeeById(request.getId());
    }
}