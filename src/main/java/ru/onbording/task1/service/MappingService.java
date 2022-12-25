package ru.onbording.task1.service;

import com.baeldung.springsoap.gen.Employee;
import org.springframework.stereotype.Service;
import ru.onbording.task1.model.EmployeeDto;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@Service
public class MappingService {
    public EmployeeDto convertEmployeeSoapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setGender(employee.getGender());
        employeeDto.setBirthDay(xmlGregorianCalendarToLocalDate(employee.getBirthDay()));
        employeeDto.setSalary(Double.parseDouble(employee.getSalary()));
        employeeDto.setStartDate(xmlGregorianCalendarToLocalDate(employee.getStartDate()));
        employeeDto.setEndDate(xmlGregorianCalendarToLocalDate(employee.getEndDate()));
        employeeDto.setDescription(employee.getDescription());
        return employeeDto;
    }

    public Employee convertEmployeeDtotoSoap(EmployeeDto employeeDto) {
        com.baeldung.springsoap.gen.Employee employeeSoap = new com.baeldung.springsoap.gen.Employee();
        employeeSoap.setId(employeeDto.getId());
        employeeSoap.setLastName(employeeDto.getLastName());
        employeeSoap.setFirstName(employeeDto.getFirstName());
        employeeSoap.setMiddleName(employeeDto.getMiddleName());
        employeeSoap.setPhone(employeeDto.getPhone());
        employeeSoap.setPosition(employeeDto.getPosition());
        employeeSoap.setGender(employeeDto.getGender());
        employeeSoap.setBirthDay(localDateToXmlGregorianCalendar(employeeDto.getBirthDay()));
        employeeSoap.setSalary(employeeDto.getSalary().toString());
        employeeSoap.setStartDate(localDateToXmlGregorianCalendar(employeeDto.getStartDate()));
        employeeSoap.setEndDate(localDateToXmlGregorianCalendar(employeeDto.getEndDate()));
        employeeSoap.setDescription(employeeDto.getDescription());
        return employeeSoap;
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar(LocalDate localDate) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    private LocalDate xmlGregorianCalendarToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        try {
            return LocalDate.of(
                    xmlGregorianCalendar.getYear(),
                    xmlGregorianCalendar.getMonth(),
                    xmlGregorianCalendar.getDay());
        } catch (Exception ex) {
            return null;
        }
    }
}
