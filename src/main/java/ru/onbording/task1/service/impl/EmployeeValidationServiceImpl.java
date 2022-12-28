package ru.onbording.task1.service.impl;

import ru.onbording.task1.entity.Employee;
import ru.onbording.task1.service.EmployeeValidationService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidationServiceImpl implements EmployeeValidationService { //todo должен быть бином
    public List<String> checkEmployeeData(Employee employee) {
        List<String> messages = new ArrayList<>();

        if (employee.getEndDate() != null && employee.getStartDate().isAfter(employee.getEndDate())) {
            messages.add("Дата увольнения не может быть больше даты приема"); //todo текст для пользователя желательно вынести из кода, посмотри ResourceBundle
        }
        if (checkGender(employee.getGender())) {
            messages.add("Пол должен быть 'M' или 'F'");
        }
        if (checkSalary(employee.getSalary(), employee.getPosition())) {
            messages.add(String.format("Зарплата %s не соответствует долженности %s", employee.getSalary(), employee.getPosition()));
        }
        if (checkPhone(employee.getPhone())) {
            messages.add(String.format("Номер телефона %s не соответствует формату ХХХХХХХХХХХ", employee.getPhone())); //todo не совсем понятно то это за формат)
        }
        return messages;
    }

    private boolean checkGender(String gender) { //todo просто возвращай булево выражение, не используя if
        if (gender.equals("F") || gender.equals("M")) { //todo типы гендера лучше вынести в отдельный enum
            return false;
        }
        return true;
    }

    private boolean checkPhone(String phone) { //todo просто возвращай булево выражение, не используя if
        if (phone == null || (phone.length() == 11 && phone.matches("\\d+"))) { //todo количество цифр можно указать в регулярном выражении
            return false;
        }
        return true;
    }

    private boolean checkSalary(Double salary, String position) { //todo куча магических слов и чисел, стоит всё вынести в один enum
        if (position.equals("assistant") && salary >= 100000 && salary <= 200000) {
            return false;
        }
        if (position.equals("manager") && salary >= 200000 && salary <= 300000) {
            return false;
        }
        if (position.equals("analyst") && salary >= 300000 && salary < 400000) {
            return false;
        }
        if (position.equals("accountant") && salary >= 500000 && salary <= 600000) {
            return false;
        }
        if (position.equals("director") && salary >= 600000 && salary <= 1000000) {
            return false;
        }
        return true;
    }
}
