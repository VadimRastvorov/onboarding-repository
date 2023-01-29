package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.EmployeeRepository;
import ru.onbording.employeeservice.service.kafka.ProducerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private ProducerService producerService;

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

    public List<ResponseEmployeeMessagesDto> saveEmployeeList(List<EmployeeDto> employeeDtoList) {
        List<ResponseEmployeeMessagesDto> listResponseEmployeeMessagesDto = new ArrayList<>();
        for (EmployeeDto employeeDto : employeeDtoList) {
            List<String> messages = employeeValidationService.checkData(employeeDto);
            if (messages.size() == 0) {
                producerService.produceEmployee(employeeDto);
                messages.add(MessageBundleConfig.getMessage("employee.addRow", employeeDto.getId()));
            }
            listResponseEmployeeMessagesDto.add(createResponseEmployeeMessagesDto(employeeDto, messages));
        }
        return listResponseEmployeeMessagesDto;
    }

    public ResponseEmployeeMessagesDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = getEmployeeByEmployeeDto(employeeDto);
        List<String> messages = employeeValidationService.checkData(employeeMapper.entityToDto(employee));
        if (messages.size() > 0) {
            return createResponseEmployeeMessagesDto
                    (employeeMapper.entityToDto(fetchEmployeeById(employeeDto.getId())), messages);
        }
        employee = employeeRepository.save(employee);
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

    private Employee getEmployeeByEmployeeDto(EmployeeDto employeeDto) {  //todo лучше перед апдейтом EmployeeDto замаппить в Employee, дабы не заниматься парсингом тут //done
        Employee employeeFromDto = employeeMapper.dtoToEntity(employeeDto);
        Employee employeeFetch = fetchEmployeeById(employeeDto.getId());
        employeeFetch = Employee.builder()
                .id(employeeFetch.getId())
                .birthday(getValOrDefault(employeeFromDto.getBirthday(), employeeFetch.getBirthday()))
                .gender(getValOrDefault(employeeFromDto.getGender(), employeeFetch.getGender()))
                .phone(getValOrDefault(employeeFromDto.getPhone(), employeeFetch.getPhone()))
                .lastName(getValOrDefault(employeeFromDto.getLastName(), employeeFetch.getLastName()))
                .firstName(getValOrDefault(employeeFromDto.getFirstName(), employeeFetch.getFirstName()))
                .middleName(getValOrDefault(employeeFromDto.getMiddleName(), employeeFetch.getMiddleName()))
                .position(getValOrDefault(employeeFromDto.getPosition(), employeeFetch.getPosition()))
                .description(getValOrDefault(employeeFromDto.getDescription(), employeeFetch.getDescription()))
                .endDate(getValOrDefault(employeeFromDto.getEndDate(), employeeFetch.getEndDate()))
                .startDate(getValOrDefault(employeeFromDto.getStartDate(), employeeFetch.getStartDate()))
                .salary(getValOrDefault(employeeFromDto.getSalary(), employeeFetch.getSalary()))
                .tasks(employeeFetch.getTasks())
                .build();
        return employeeFetch;
    }
}
