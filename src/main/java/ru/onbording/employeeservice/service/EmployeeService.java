package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.controller.dto.EmployeeDto;
import ru.onbording.employeeservice.controller.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.controller.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final EmployeeValidationService employeeValidationService;

    @Autowired
    private final MappingService mappingService;

    public EmployeeDto fetchEmployeeById(Long id) {
        return mappingService.employeeToEmployeeDto(employeeRepository.findById(id).orElseThrow()); //todo всё таки не хватает ответа при отсутствии такого id
    }

    public ResponseEmployeeMessagesDto saveEmployee(EmployeeDto employeeDto) {
        List<String> messages = employeeValidationService.checkData(employeeDto);
        if (messages.size() == 0) {
            Employee employee = employeeRepository.save(mappingService.employeeDtoToEmployee(employeeDto));
            messages.add(String.format(
                    MessageBundleConfig.getMessageBundleValue("employee.addRow"),
                    employee.getId()));
            employeeDto = mappingService.employeeToEmployeeDto(employee); //todo желательно полученные параметры не менять в процессе, лучше создавать новые
        }
        return mappingService.listStringToResponseEmployeeMessagesDto(messages,
                employeeDto);
    }

    public ResponseEmployeeMessagesDto updateEmployee(EmployeeDto employeeDto) {
        List<String> messages = employeeValidationService.checkData(employeeDto);
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow();
        if (messages.size() == 0) {
            employee = employeeRepository.save(mappingService.employeeDtoToEmployeeUpdate(employeeDto,
                    employee));
            messages.add(String.format(
                    MessageBundleConfig.getMessageBundleValue("employee.updateRow"),
                    employee.getId()));
            employeeDto = mappingService.employeeToEmployeeDto(employee); //todo тут убери, получилось дублирование
        }
        //todo желательно полученные параметры не менять в процессе, лучше создавать новые
        employeeDto = mappingService.employeeToEmployeeDto(employee); //todo вот тут повторное выполнение действия выше
        return mappingService.listStringToResponseEmployeeMessagesDto(messages,
                employeeDto);
    }

    public List<EmployeeDto> fetchEmployeeAll() {
        return employeeRepository.findAllByOrderByIdAsc()
                .stream()
                .map(employee -> {
                    return mappingService.employeeToEmployeeDto(employee); //todo idea подсказывает как сделать красивее)
                })
                .collect(Collectors.toList());
    }

    public ResponseMessageDto deleteEmployeeById(Long id) {
        employeeRepository.deleteEmployeeById(id);
        return ResponseMessageDto
                .builder()
                .message(String.format(
                        MessageBundleConfig.getMessageBundleValue("employee.deleteRow"), id))
                .build();
    }

    public void deleteOneRowEmployee() {
        employeeRepository.deleteEmployee();
    }
}
