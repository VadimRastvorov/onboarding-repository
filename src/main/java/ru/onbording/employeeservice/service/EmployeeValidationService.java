package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.controller.dto.EmployeeDto;
import ru.onbording.employeeservice.controller.dto.Gender;
import ru.onbording.employeeservice.controller.dto.Position;
import ru.onbording.employeeservice.repository.EmployeeTaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeValidationService { //todo должен быть бином //done

    @Autowired
    private final EmployeeTaskRepository employeeTaskRepository;

    public List<String> checkData(EmployeeDto employeeDto) {

        List<String> messages = new ArrayList<>();

        if (employeeDto.getEndDate() != null && employeeDto.getStartDate().isAfter(employeeDto.getEndDate())) {
            messages.add(MessageBundleConfig.getMessageBundleValue("employee.endDate")); //todo текст для пользователя желательно вынести из кода, посмотри ResourceBundle
        }
        if (employeeDto.getGender() != null && checkGender(employeeDto.getGender())) {
            messages.add(MessageBundleConfig.getMessageBundleValue("employee.gender"));
        }
        if (!(employeeDto.getSalary() == null && employeeDto.getPosition() == null)
                && checkSalary(employeeDto.getSalary(), employeeDto.getPosition())) {
            messages.add(String.format(
                    MessageBundleConfig.getMessageBundleValue("employee.salary"),
                    employeeDto.getSalary(),
                    employeeDto.getPosition()));
        }
        if (checkPhone(employeeDto.getPhone())) {
            messages.add(String.format(MessageBundleConfig.getMessageBundleValue("employee.phone"), employeeDto.getPhone())); //todo не совсем понятно то это за формат)
        }
        if (!Position.checkTasks(employeeTaskRepository.countTasksByEmployeeId(employeeDto.getId()), employeeDto.getPosition())) {
            messages.add(String.format(MessageBundleConfig.getMessageBundleValue("task.taskCount")));
        }
        return messages;
    }

    private boolean checkGender(String gender) { //todo просто возвращай булево выражение, не используя if
        //todo типы гендера лучше вынести в отдельный enum //done
        return !Gender.checkValue(gender);
    }

    private boolean checkPhone(String phone) { //todo просто возвращай булево выражение, не используя if //done
        //todo количество цифр можно указать в регулярном выражении //done
        return !(phone == null || phone.matches("\\d{11}"));
    }

    private boolean checkSalary(double salary, String position) { //todo куча магических слов и чисел, стоит всё вынести в один enum // done
        return !Position.checkValue(position, salary);
    }
}
