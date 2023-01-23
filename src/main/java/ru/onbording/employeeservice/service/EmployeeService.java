package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final EmployeeValidationService employeeValidationService;

    @Autowired
    private final Mapper<Employee, EmployeeDto> employeeMapper;

    @Autowired
    private final Mapper<Task, TaskDto> taskMapper; //todo не используется?

    public EmployeeDto fetchEmployeeDtoById(Long id) {
        return employeeMapper.entityToDto(fetchEmployeeById(id));
    }

    public ResponseEmployeeMessagesDto saveEmployee(EmployeeDto employeeDto) {
        List<String> messages = employeeValidationService.checkData(employeeDto);
        if (messages.size() > 0) {
            return createResponseEmployeeMessagesDto(employeeDto, messages);
        }
        Employee employee = employeeRepository.save(employeeMapper.dtoToEntity(employeeDto));
        messages.add(MessageBundleConfig.getMessage("employee.addRow", employee.getId()));
        return createResponseEmployeeMessagesDto(employeeMapper.entityToDto(employee), messages);
    }

    public ResponseEmployeeMessagesDto updateEmployee(EmployeeDto employeeDto) {
        List<String> messages = employeeValidationService.checkData(employeeDto);
        if (messages.size() > 0) {
            return createResponseEmployeeMessagesDto
                    (employeeMapper.entityToDto(fetchEmployeeById(employeeDto.getId())), messages);
        }
        Employee employee = employeeRepository.save(getEmployeeByEmployeeDto(employeeDto));
        messages.add(MessageBundleConfig.getMessage("employee.updateRow", employeeDto.getId()));
        return createResponseEmployeeMessagesDto(employeeMapper.entityToDto(employee), messages);
    }

    public List<EmployeeDto> fetchEmployeeAll() {
        return employeeRepository.findAllByOrderByIdAsc()
                .stream()
                .map(employeeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ResponseMessageDto deleteEmployeeById(Long id) {
        employeeRepository.delete(fetchEmployeeById(id));
        return ResponseMessageDto
                .builder()
                .message(MessageBundleConfig.getMessage("employee.deleteRow", id))
                .build();
    }

    public void deleteOneRowEmployee() {
        employeeRepository.deleteEmployee();
    }

    public Employee fetchEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private ResponseEmployeeMessagesDto createResponseEmployeeMessagesDto
            (EmployeeDto employeeDto, List<String> messages) {
        return ResponseEmployeeMessagesDto.builder()
                .employeeDto(employeeDto)
                .messages(messages.stream()
                        .map(this::createResponseMessageDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private ResponseMessageDto createResponseMessageDto(String message) {
        return ResponseMessageDto.builder()
                .message(message)
                .build();
    }

    private <T> T getValOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    private Employee getEmployeeByEmployeeDto(EmployeeDto employeeDto) {  //todo лучше перед апдейтом EmployeeDto замаппить в Employee, дабы не заниматься парсингом тут
        Employee employee = fetchEmployeeById(employeeDto.getId());
        List<Task> taskList = employee.getTasks(); //todo не используется?
        employee = Employee.builder()
                .id(employee.getId())
                .birthday(Objects.nonNull(employeeDto.getBirthday()) ?
                        LocalDate.parse(employeeDto.getBirthday()) : employee.getBirthday())
                .gender(getValOrDefault(employeeDto.getGender(), employee.getGender()))
                .phone(getValOrDefault(employeeDto.getPhone(), employee.getPhone()))
                .lastName(getValOrDefault(employeeDto.getLastName(), employee.getLastName()))
                .firstName(getValOrDefault(employeeDto.getFirstName(), employee.getFirstName()))
                .middleName(getValOrDefault(employeeDto.getMiddleName(), employee.getMiddleName()))
                .position(getValOrDefault(employeeDto.getPosition(), employee.getPosition()))
                .description(getValOrDefault(employeeDto.getDescription(), employeeDto.getDescription()))
                .endDate(Objects.nonNull(employeeDto.getEndDate()) ?
                        LocalDate.parse(employeeDto.getEndDate()) : employee.getEndDate())
                .startDate(Objects.nonNull(employeeDto.getStartDate()) ?
                        LocalDate.parse(employeeDto.getStartDate()) : employee.getStartDate())
                .salary(Objects.nonNull(employeeDto.getSalary()) ?
                        Double.parseDouble(employeeDto.getSalary()) : employee.getSalary())
                .build();
        //employee.getTasks().clear();
        //employee.getTasks().addAll(taskList);
        return employee;
    }
}
