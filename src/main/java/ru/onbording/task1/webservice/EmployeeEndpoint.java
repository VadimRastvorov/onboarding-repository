package ru.onbording.task1.webservice;

import com.baeldung.springsoap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.onbording.task1.model.EmployeeDto;
import ru.onbording.task1.repository.EmployeeRepository;
import ru.onbording.task1.service.MappingService;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    private MappingService mappingService = new MappingService();
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        response.setEmployee(mappingService.convertEmployeeDtotoSoap(employeeRepository.findById(request.getId()).get()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setEmployeeRequest")
    @ResponsePayload
    public SetEmployeeResponse setEmployee(@RequestPayload SetEmployeeRequest request) {
        SetEmployeeResponse response = new SetEmployeeResponse();
        EmployeeDto employee = employeeRepository.save(mappingService.convertEmployeeSoapToDto(request.getEmployee()));
        response.setId(employee.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse getEmployees(@RequestPayload GetEmployeesRequest request) {
        GetEmployeesResponse response = new GetEmployeesResponse();
        for (EmployeeDto employee : employeeRepository.findAll()) {
            response.getEmployee().add(mappingService.convertEmployeeDtotoSoap(employee));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        employeeRepository.deleteById(request.getId());
        response.setStatus("Delete Row");
        return response;
    }
}