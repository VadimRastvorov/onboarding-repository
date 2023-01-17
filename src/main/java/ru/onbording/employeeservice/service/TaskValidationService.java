package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.repository.TaskRepository;
import ru.onbording.employeeservice.type.Position;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskValidationService {
    @Autowired
    private final TaskRepository taskRepository;

    public List<String> checkData(Employee employee) {
        List<String> messages = new ArrayList<>();
        if (checkTasks(employee.getId(), employee.getPosition())) {
            messages.add(MessageBundleConfig.getMessage("task.taskCount"));
        }
        return messages;
    }

    private boolean checkTasks(Long id, String position) {
        if (id == null) {
            return false;
        }
        int tasksCount = taskRepository.countTasksByEmployeeId(id);
        for (Position env : Position.values()) {
            if (env.name().equals(position)) {
                return !(tasksCount <= env.getTasksMax());
            }
        }
        return true;
    }
}
