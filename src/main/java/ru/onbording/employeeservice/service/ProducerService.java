package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.TaskDto;

@Slf4j
@Service
@AllArgsConstructor
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, EmployeeDto> kafkaTemplateEmployee;

    @Autowired
    private KafkaTemplate<String, TaskDto> kafkaTemplateTask;

    public void produceEmployee(EmployeeDto employeeDto) {
        log.info("Producing the message: {}", employeeDto);
        kafkaTemplateEmployee.send("employees", employeeDto);
    }

    public void produceTask(TaskDto taskDto) {
        log.info("Producing the message: {}", taskDto);
        kafkaTemplateTask.send("tasks", taskDto);
    }
}