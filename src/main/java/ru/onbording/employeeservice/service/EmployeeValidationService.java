package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.repository.TaskRepository;
import ru.onbording.employeeservice.type.Gender;
import ru.onbording.employeeservice.type.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeValidationService {

    @Autowired
    private final TaskRepository taskRepository;

    public List<String> checkData(EmployeeDto employeeDto) { //todo метод разросся, разбей логику внутри ещё на методы поменьше

        List<String> messages = new ArrayList<>();

        if (checkWorkPeriod(employeeDto.getStartDate(), employeeDto.getEndDate())) {
            messages.add(MessageBundleConfig.getMessage("employee.endDate"));
        }
        if (checkGender(employeeDto.getGender())) {
            messages.add(MessageBundleConfig.getMessage("employee.gender"));
        }
        if (checkSalary(employeeDto.getSalary(), employeeDto.getPosition())) {
            messages.add(MessageBundleConfig.getMessage("employee.salary", employeeDto.getSalary(), employeeDto.getPosition()));
        }
        if (checkPhone(employeeDto.getPhone())) {
            messages.add(MessageBundleConfig.getMessage("employee.phone", employeeDto.getPhone()));
        }
        if (checkTasks(employeeDto.getId(), employeeDto.getPosition())) {
            messages.add(MessageBundleConfig.getMessage("task.taskCount"));
        }
        return messages;
    }

    private boolean checkWorkPeriod(String startDate, String endDate) {
        if (Strings.isEmpty(endDate) || Strings.isEmpty(startDate)) {
            return false;
        }
        return LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate));
    }

    private boolean checkGender(String gender) {
        for (Gender env : Gender.values()) {
            if (env.name().equals(gender)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPhone(String phone) {
        return !(phone == null || phone.matches("\\d{11}"));
    }

    private boolean checkSalary(String salary, String position) {
        if (Strings.isEmpty(salary) && Strings.isEmpty(position)) {
            return false;
        }
        if (Strings.isEmpty(salary)) {
            return true;
        }
        double salaryDouble = Double.parseDouble(salary);
        for (Position env : Position.values()) {
            if (env.name().equals(position)) {
                return !(env.getSalaryMax() >= salaryDouble && env.getSalaryMin() <= salaryDouble);
            }
        }
        return true;
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
