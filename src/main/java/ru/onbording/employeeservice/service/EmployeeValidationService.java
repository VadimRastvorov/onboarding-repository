package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
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

    public List<String> checkData(EmployeeDto employeeDto) {
        List<String> messages = new ArrayList<>();
        checkRequiredData(messages, employeeDto);
        checkPosition(messages, employeeDto);
        checkGender(messages, employeeDto);
        checkWorkPeriod(messages, employeeDto);
        checkPhone(messages, employeeDto);
        return messages;
    }

    private void checkRequiredData(List<String> messages, EmployeeDto employeeDto) {
        if (objectIsNull(employeeDto.getGender()) || employeeDto.getGender().isEmpty()) {
            fillRequiredData(messages, "gender");
        }
        if (objectIsNull(employeeDto.getPosition()) || employeeDto.getPosition().isEmpty()) {
            fillRequiredData(messages, "position");
        }
        if (objectIsNull(employeeDto.getSalary()) || employeeDto.getSalary().isEmpty()) {
            fillRequiredData(messages, "salary");
        }
        if (objectIsNull(employeeDto.getLastName()) || employeeDto.getLastName().isEmpty()) {
            fillRequiredData(messages, "lastName");
        }
        if (objectIsNull(employeeDto.getFirstName()) || employeeDto.getFirstName().isEmpty()) {
            fillRequiredData(messages, "firstName");
        }
        if (objectIsNull(employeeDto.getBirthday()) || employeeDto.getBirthday().isEmpty()) {
            fillRequiredData(messages, "birthday");
        }
        if (objectIsNull(employeeDto.getStartDate()) || employeeDto.getStartDate().isEmpty()) {
            fillRequiredData(messages, "startDate");
        }
    }

    private void checkPosition(List<String> messages, EmployeeDto employeeDto) {
        if (checkValidPosition(employeeDto.getPosition())) {
            messages.add(MessageBundleConfig
                    .getMessage("employee.validPosition", employeeDto.getPosition()));
            return;
        }
        if (checkTasks(employeeDto.getId(), employeeDto.getPosition())) {
            messages.add(MessageBundleConfig
                    .getMessage("task.taskCount", employeeDto.getId()));
        }
        if (checkSalary(employeeDto.getSalary(), employeeDto.getPosition())) {
            messages.add(MessageBundleConfig
                    .getMessage("employee.salary", employeeDto.getSalary(), employeeDto.getPosition()));
        }
    }

    private void checkGender(List<String> messages, EmployeeDto employeeDto) {
        if (checkValidGender(employeeDto.getGender())) {
            messages.add(MessageBundleConfig.getMessage("employee.validGender"));
        }
    }

    private void checkWorkPeriod(List<String> messages, EmployeeDto employeeDto) {
        if (objectIsNull(employeeDto.getEndDate()) || employeeDto.getEndDate().isEmpty()) {
            return;
        }
        if (objectIsNull(employeeDto.getStartDate()) || employeeDto.getStartDate().isEmpty()) {
            return;
        }
        if (LocalDate.parse(employeeDto.getStartDate()).isAfter(LocalDate.parse(employeeDto.getEndDate()))) {
            messages.add(MessageBundleConfig.getMessage("employee.endDate"));
        }
    }

    private void checkPhone(List<String> messages, EmployeeDto employeeDto) {
        if (objectIsNull(employeeDto.getPhone()) || employeeDto.getPhone().isEmpty()) {
            if (employeeDto.getPosition().equals(Position.DIRECTOR.name())) {
                messages.add(MessageBundleConfig
                        .getMessage("employee.phoneDirector", employeeDto.getPosition(), "phone"));
            }
        }
        if (checkValidPhone(employeeDto.getPhone())) {
            messages.add(MessageBundleConfig
                    .getMessage("employee.validPhone", employeeDto.getPhone()));
        }
    }

    private void fillRequiredData(List<String> messages, String field) {
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", field));
    }

    private boolean objectIsNull(Object obj) {
        return obj == null;
    }

    private boolean checkValidGender(String gender) {
        if (objectIsNull(gender) || gender.isBlank()) {
            return false;
        }
        for (Gender env : Gender.values()) {
            if (env.name().equals(gender)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkValidPosition(String position) {
        if (objectIsNull(position) || position.isBlank()) {
            return false;
        }
        for (Position env : Position.values()) {
            if (env.name().equals(position)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkValidPhone(String phone) {
        return !(phone == null || phone.matches("\\d{11}"));
    }

    private boolean checkSalary(String salary, String position) {
        if ((objectIsNull(salary) || salary.isEmpty()) && (objectIsNull(position) || position.isEmpty())) {
            return false;
        }
        if (objectIsNull(salary) || salary.isEmpty()) {
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
        if (objectIsNull(id)) {
            return false;
        }
        if (objectIsNull(position) || position.isBlank()) {
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
