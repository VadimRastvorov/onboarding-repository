package ru.onbording.employeeservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.service.TaskService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ResponseMessageDto> deleteTaskById(@PathVariable String uuid) {
        log.info("вызов метода deleteTask c uuid: '{}'", uuid);
        return new ResponseEntity<>(taskService.deleteTaskById(uuid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> fetchAllTask() {
        log.info("вызов метода allEmployeeTask");
        return new ResponseEntity<>(taskService.fetchTaskAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<TaskDto>> fetchTaskByEmployeeId(@PathVariable String id) {
        log.info("вызов метода fetchEmployeeTask");
        return new ResponseEntity<>(taskService.fetchTaskByEmployeeId(id), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDto> fetchTaskById(@PathVariable String uuid) {
        log.info("вызов метода fetchTaskId c uuid: '{}'", uuid);
        return new ResponseEntity<>(taskService.fetchTaskDtoById(uuid), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseTaskMessagesDto> createTask(@RequestBody TaskDto taskDto) {
        log.info("вызов метода createTask {}", taskDto.toString());
        return new ResponseEntity<>(taskService.saveTask(taskDto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseMessageDto> updateTask(@RequestBody TaskDto taskDto) {
        log.info("вызов метода updateTask {}", taskDto.toString());
        return new ResponseEntity<>(taskService.updateTask(taskDto), HttpStatus.OK);
    }
}