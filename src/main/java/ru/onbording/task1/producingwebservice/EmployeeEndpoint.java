package ru.onbording.task1.producingwebservice; //todo название директории не соответствует содержимому

import com.baeldung.springsoap.gen.Employee;
import com.baeldung.springsoap.gen.GetEmployeeRequest;
import com.baeldung.springsoap.gen.GetEmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.onbording.task1.repository.EmployeeRepository;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private EmployeeRepository employeeRepository; //todo должен быть финальным

    @Autowired
    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        //todo для работы с БД создай отдельный класс EmployeeService, так же обработай случай, если такого id нет в бд
        ru.onbording.task1.model.Employee employee = employeeRepository.findById(request.getId()).get(); //todo чтоб такого не было назови, например, Employee и EmployeeDto
        Employee employee1 = new Employee(); //todo название объекта не катит

        //todo то что ниже - можно сделать изящнее
        employee1.setId(employee.getId());
        employee1.setLastName(employee.getLastName());
        employee1.setFirstName(employee.getFirstName());
        employee1.setMiddleName(employee.getMiddleName());
        employee1.setPhone(employee.getPhone());
        employee1.setPosition(employee.getPosition());
        //employee1.setBirthDay(employee.getBirthDay());
        employee1.setSalary(employee.getSalary().toString());
        //employee1.setStartDate(employee.getStartDate());
        //employee1.setEndDate(employee.getEndDate());
        employee1.setDescription(employee.getDescription());




        response.setEmployee(employee1);
        return response;
    }
}