package ru.onbording.employeeservice.service.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.service.EmployeeService;
import ru.onbording.employeeservice.service.TaskService;

@Slf4j
@AllArgsConstructor
@Service
public class ConsumerService {

    @Autowired
    private final EmployeeService employeeService;

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