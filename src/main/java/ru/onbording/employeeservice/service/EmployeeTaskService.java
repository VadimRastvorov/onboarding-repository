package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.controller.dto.EmployeeTaskDto;
import ru.onbording.employeeservice.controller.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.entity.EmployeeTask;
import ru.onbording.employeeservice.repository.EmployeeRepository;
import ru.onbording.employeeservice.repository.EmployeeTaskRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class EmployeeTaskService {
    @Autowired
    private final MappingService mappingService;
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final EmployeeTaskRepository employeeTaskRepository;

    public EmployeeTaskDto fetchEmployeeTaskById(UUID id) {
        return mappingService.employeeTaskToEmployeeTaskDto(employeeTaskRepository.findTaskByTaskId(id)); //todo не хватает ответа, если такого uuid нет
    }

    public ResponseMessageDto saveEmployeeTask(EmployeeTaskDto employeeTaskDto) {
        //todo лучше работать с репозиторием для тасков и сохранять через, раз у dto уже есть employeeId
        //todo через этот метод можно обойти ограничение по количеству тасков у той или иной должности
        Employee employee = employeeRepository.findById(employeeTaskDto.getEmployeeId()).orElseThrow();
        employee.getEmployeeTasks().add(EmployeeTask //todo нужно сделать сервис TaskMapper
                .builder()
                .description(employeeTaskDto.getDescription())
                .build());
        employeeRepository.save(employee);
        return ResponseMessageDto.builder()
                .message(String.format(
                        MessageBundleConfig.getMessageBundleValue("task.addRow")))
                .build();
    }

    public ResponseMessageDto updateEmployeeTask(EmployeeTaskDto employeeTaskDto) {
        int updateCount =
                employeeTaskRepository.updateTaskDescriptionById(employeeTaskDto.getId(), employeeTaskDto.getDescription());
        return ResponseMessageDto.builder()
                .message(String.format(
                        MessageBundleConfig.getMessageBundleValue("task.updateRow")))
                .build();
    }

    public List<EmployeeTaskDto> fetchEmployeeTaskAll() {
        return employeeTaskRepository.findAllTask()
                .stream()
                .map(employeeTask -> {
                    return mappingService.employeeTaskToEmployeeTaskDto(employeeTask);
                })
                .collect(Collectors.toList());
    }

    public ResponseMessageDto deleteEmployeeTaskById(UUID id) {
        employeeTaskRepository.deleteTaskById(id); //todo что будет если в запросе указали не существующий uuid?)
        return ResponseMessageDto
                .builder()
                .message(String.format(
                        MessageBundleConfig.getMessageBundleValue("task.deleteRow"), id))
                .build();
    }
}
