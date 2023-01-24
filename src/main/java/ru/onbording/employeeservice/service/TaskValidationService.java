package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.repository.TaskRepository;
import ru.onbording.employeeservice.type.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskValidationService {
    @Autowired
    private final TaskRepository taskRepository;

    public List<String> checkData(TaskDto taskDto) {
        if (checkTaskParamEmployee(taskDto.getEmployeeId())) {
            return Arrays.asList(MessageBundleConfig.getMessage("task.checkEmployeeId"));
        }

        List<String> messages = new ArrayList<>();
        Long taskEmployeeId = Long.parseLong(taskDto.getEmployeeId());
        String position = taskRepository.fetchPositionByEmployeeId(taskEmployeeId);

        if (checkTaskEmployeePosition(position)) {
            return Arrays.asList(MessageBundleConfig.getMessage("task.checkEmployee", taskEmployeeId));
        }

        if (checkTasksCount(taskEmployeeId, position)) {
            messages.add(MessageBundleConfig.getMessage("task.taskCount", taskEmployeeId, position));
        }
        return messages;
    }

    private boolean checkTaskParamEmployee(String employeeId) {
        return employeeId == null || employeeId.isEmpty();
    }

    private boolean checkTaskEmployeePosition(String position) {
        return position == null || position.isEmpty();
    }

    private boolean checkTasksCount(Long employeeId, String position) {
        if (employeeId == null) {
            return false;
        }
        int tasksCount = taskRepository.countTasksByEmployeeId(employeeId);
        for (Position env : Position.values()) {
            if (env.name().equals(position)) {
                return !(tasksCount <= env.getTasksMax());
            }
        }
        return true;
    }
}
