package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.TaskRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class TaskService {

    @Autowired
    private final Mapper<Task, TaskDto> taskMapper;

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    private final TaskValidationService taskValidationService;

    @Autowired
    private final TaskRepository taskRepository;

    public TaskDto fetchTaskDtoById(String uuid) {
        log.info("вызов метода TaskService.fetchTaskById, id запрашиваемой записи {}", uuid);
        return taskMapper.entityToDto(fetchTaskById(UUID.fromString(uuid)));
    }

    public List<TaskDto> fetchTaskByEmployeeId(String id) {
        log.info("вызов метода TaskService.fetchTaskByEmployeeId {}", id);
        List<TaskDto> taskDtoList = taskRepository.findByEmployeeId(Long.parseLong(id))
                .stream()
                .map(taskMapper::entityToDto)
                .collect(Collectors.toList());
        if (taskDtoList.size() == 0) {
            throw new ResourceNotFoundException(Long.parseLong(id));
        }
        return taskDtoList;
    }


    public ResponseTaskMessagesDto saveTask(TaskDto taskDto) {
        log.info("вызов метода TaskService.saveTask {}", taskDto.toString());
        Employee employee = employeeService.fetchEmployeeById(Long.parseLong(taskDto.getEmployeeId())); //вызов для проверки работника
        List<String> messages = taskValidationService.checkData(employee);
        if (messages.size() > 0) {
            return createResponseTaskMessagesDto(taskDto, messages);
        }
        Task task = taskRepository.save(taskMapper.dtoToEntity(taskDto));
        messages.add(MessageBundleConfig.getMessage("task.addRow", task.getUuid(), employee.getLastName()));
        return createResponseTaskMessagesDto(taskMapper.entityToDto(task), messages);
    }

    public ResponseMessageDto updateTask(TaskDto taskDto) {
        log.info("вызов метода TaskService.updateTask {}", taskDto.toString());
        Task task = fetchTaskById(UUID.fromString(taskDto.getUuid()));
        task.setDescription(taskDto.getDescription());
        taskRepository.save(task);
        return createResponseMessageDto(MessageBundleConfig.getMessage("task.updateRow", taskDto.getUuid()));
    }

    public List<TaskDto> fetchTaskAll() {
        log.info("вызов метода TaskService.fetchTaskAll");
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ResponseMessageDto deleteTaskById(String uuid) {
        log.info("вызов метода TaskService.deleteTaskById, id удаляемой записи {}", uuid);
        taskRepository.delete(fetchTaskById(UUID.fromString(uuid)));
        return createResponseMessageDto(MessageBundleConfig.getMessage("task.deleteRow", uuid));
    }

    private Task fetchTaskById(UUID uuid) {
        return taskRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(uuid));
    }

    private ResponseMessageDto createResponseMessageDto(String message) {
        return ResponseMessageDto.builder()
                .message(message)
                .build();
    }

    private ResponseTaskMessagesDto createResponseTaskMessagesDto(TaskDto taskDto, List<String> messages) {
        return ResponseTaskMessagesDto.builder()
                .taskDto(taskDto)
                .messages(messages.stream()
                        .map(this::createResponseMessageDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
