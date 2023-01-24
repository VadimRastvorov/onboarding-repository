package ru.onbording.employeeservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.TaskDto;

@Slf4j
@Service
public class ConsumerService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    @KafkaListener(topics = "employees", groupId = "message_group_id")
    public void consumeEmployees(EmployeeDto employeeDto) {
        log.info("Consuming the message: {}", employeeDto);
        employeeService.saveEmployee(
                employeeDto);
    }

    @KafkaListener(topics = "tasks", groupId = "message_group_id")
    public void consumeTasks(TaskDto taskDto) {
        log.info("Consuming the message: {}", taskDto);
        taskService.saveTask(
                taskDto);
    }
}