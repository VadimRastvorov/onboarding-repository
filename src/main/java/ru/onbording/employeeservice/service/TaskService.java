package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.TaskRepository;
import ru.onbording.employeeservice.service.kafka.ProducerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

    @Autowired
    private final Mapper<Task, TaskDto> taskMapper;

    @Autowired
    private final TaskValidationService taskValidationService;

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private ProducerService producerService;

    public TaskDto fetchTaskDtoById(String uuid) {
        return taskMapper.entityToDto(fetchTaskById(UUID.fromString(uuid)));
    }

    public List<TaskDto> fetchTaskByEmployeeId(String id) {
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
        List<String> messages = taskValidationService.checkData(taskDto);
        if (messages.size() > 0) {
            return createResponseTaskMessagesDto(taskDto, messages);
        }
        Task task = taskRepository.save(taskMapper.dtoToEntity(taskDto));
        messages.add(MessageBundleConfig.getMessage("task.addRow", task.getUuid(), taskDto.getEmployeeId()));
        return createResponseTaskMessagesDto(taskMapper.entityToDto(task), messages);
    }

    public List<ResponseTaskMessagesDto> saveTaskList(List<TaskDto> taskDtoList) {
        List<ResponseTaskMessagesDto> responseTaskMessagesDtoList = new ArrayList<>();
        for (TaskDto taskDto : taskDtoList) {
            List<String> messages = taskValidationService.checkData(taskDto);
            if (messages.size() == 0) {
                messages.add(MessageBundleConfig
                        .getMessage("task.addRow", taskDto.getUuid(), taskDto.getEmployeeId()));
                producerService.produceTask(taskDto);
            }
            responseTaskMessagesDtoList.add(createResponseTaskMessagesDto(taskDto, messages));
        }
        return responseTaskMessagesDtoList;
    }

    public ResponseMessageDto updateTask(TaskDto taskDto) {
        Task task = fetchTaskById(UUID.fromString(taskDto.getUuid()));
        task.setDescription(taskDto.getDescription());
        taskRepository.save(task);
        return createResponseMessageDto(MessageBundleConfig.getMessage("task.updateRow", taskDto.getUuid()));
    }

    public List<TaskDto> fetchTaskAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ResponseMessageDto deleteTaskById(String uuid) {
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
