package ru.onbording.employeeservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onbording.employeeservice.controller.dto.EmployeeTaskDto;
import ru.onbording.employeeservice.controller.dto.ResponseMessageDto;
import ru.onbording.employeeservice.service.EmployeeTaskService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/task")
public class EmployeeTaskController {
    @Autowired
    private final EmployeeTaskService employeeTaskService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> deleteTask(@PathVariable UUID id) {
        log.info("вызов метода deleteTask");
        return new ResponseEntity<>(employeeTaskService.deleteEmployeeTaskById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeTaskDto>> allEmployeeTask() {
        log.info("вызов метода allEmployeeTask");
        return new ResponseEntity<>(employeeTaskService.fetchEmployeeTaskAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTaskDto> getEmployeeTaskId(@PathVariable UUID id) {
        log.info("вызов метода getEmployeeTaskId");
        return new ResponseEntity<>(employeeTaskService.fetchEmployeeTaskById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseMessageDto> createEmployeeTask(@RequestBody EmployeeTaskDto employeeTaskDto) {
        log.info("вызов метода createEmployeeTask");
        return new ResponseEntity<>(employeeTaskService.saveEmployeeTask(employeeTaskDto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseMessageDto> updateEmployeeTask(@RequestBody EmployeeTaskDto employeeTaskDto) {
        log.info("вызов метода updateEmployeeTask");
        return new ResponseEntity<>(employeeTaskService.updateEmployeeTask(employeeTaskDto), HttpStatus.OK);
    }
}