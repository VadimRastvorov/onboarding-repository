package ru.onbording.employeeservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> deleteEmployeeById(@PathVariable Long id) {
        log.info("вызов метода deleteEmployee id = '{}'", id);
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployees() {
        log.info("вызов метода allEmployee");
        return new ResponseEntity<>(employeeService.fetchEmployeeAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(@PathVariable Long id) {
        log.info("вызов метода getEmployeeId id = '{}'", id);
        return new ResponseEntity<>(employeeService.fetchEmployeeDtoById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseEmployeeMessagesDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("вызов метода createEmployee {}", employeeDto.toString());
        return new ResponseEntity<>(employeeService.saveEmployee(
                employeeDto), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<List<ResponseEmployeeMessagesDto>> createListEmployees(@RequestBody List<EmployeeDto> listEmployeeDto) {
        log.info("вызов метода createListEmployee");
        List<ResponseEmployeeMessagesDto> listResponseEmployeeMessagesDto = new ArrayList<>();
        for (EmployeeDto employeeDto : listEmployeeDto) {
            listResponseEmployeeMessagesDto.add(employeeService.saveEmployee(
                    employeeDto));
        }
        return new ResponseEntity<>(listResponseEmployeeMessagesDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseEmployeeMessagesDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("вызов метода updateEmployee {}", employeeDto.toString());
        return new ResponseEntity<>(employeeService.updateEmployee(
                employeeDto), HttpStatus.OK);
    }
}