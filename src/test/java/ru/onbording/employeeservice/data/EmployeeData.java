package ru.onbording.employeeservice.data;

import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.EmployeeDto;
import ru.onbording.employeeservice.dto.ResponseEmployeeMessagesDto;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.entity.Employee;
import ru.onbording.employeeservice.entity.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EmployeeData {

    public static Employee createDataEmployeeById() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().uuid(UUID.fromString("b30cb4db-8595-43f8-99f3-c0544030300e"))
                .employeeId(5L).description("task_1_employee_5").build());
        tasks.add(Task.builder().uuid(UUID.fromString("4d30bb53-8d3a-4253-978f-40e2a7b5a7b0"))
                .employeeId(5L).description("task_2_employee_5").build());
        return Employee.builder()
                .id(5L)
                .description("description_5")
                .phone("77775555555")
                .firstName("Сидора")
                .lastName("Сидорова")
                .middleName("Сидровна")
                .position("DIRECTOR")
                .salary(666666D)
                .gender("F")
                .startDate(LocalDate.parse("2001-01-02"))
                .birthday(LocalDate.parse("1980-11-11"))
                .tasks(tasks)
                .build();
    }

    public static List<EmployeeDto> createDataEmployeeDtoList() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(EmployeeDto.builder()
                .id(1L)
                .lastName("Сидоров")
                .firstName("Сидор")
                .middleName("Сидрович")
                .position("ASSISTANT")
                .gender("M")
                .phone("77771111111")
                .salary("100001.0")
                .birthday("1984-11-12")
                .startDate("2001-01-02")
                .description("description_1")
                .build());

        employeeDtoList.add(EmployeeDto.builder()
                .id(2L)
                .lastName("Иванов")
                .firstName("Иван")
                .middleName("Иванович")
                .position("MANAGER")
                .gender("M")
                .phone("77772222222")
                .salary("200001.0")
                .birthday("1999-01-13")
                .startDate("2001-01-02")
                .description("description_2")
                .build());

        employeeDtoList.add(EmployeeDto.builder()
                .id(3L)
                .lastName("Владимиров")
                .firstName("Владимир")
                .middleName("Вовкович")
                .position("ANALYST")
                .gender("M")
                .phone("77773333333")
                .salary("300001.0")
                .birthday("1980-11-11")
                .startDate("2001-01-02")
                .description("description_3")
                .build());
        return employeeDtoList;
    }
    public static EmployeeDto createDataEmployeeDto() {
        return EmployeeDto.builder()
                .id(10L)
                .description("10L")
                .phone("77052222222")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("MANAGER")
                .salary("205000.0")
                .gender("M")
                .startDate("2012-05-11")
                .endDate("2022-09-22")
                .birthday("1990-01-02")
                .build();
    }

    public static Employee createDataEmployee() {
        return Employee.builder()
                .id(10L)
                .description("10L")
                .phone("77052222222")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("MANAGER")
                .salary(205000D)
                .gender("M")
                .startDate(LocalDate.parse("2012-05-11"))
                .endDate(LocalDate.parse("2022-09-22"))
                .birthday(LocalDate.parse("1990-01-02"))
                .build();
    }

    public static EmployeeDto createDataEmployeeDtoToUpdate(Long id) {
        return EmployeeDto.builder()
                .id(id)
                .description("5L")
                .phone("90000000000")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("DIRECTOR")
                .salary("900000.0")
                .gender("M")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().toString())
                .birthday(LocalDate.now().toString())
                .build();
    }

    public static ResponseEmployeeMessagesDto createResponseEmployeeMessagesDtoUpdate(Long id) {
        return ResponseEmployeeMessagesDto.builder()
                .employeeDto(createDataEmployeeDtoToUpdate(id))
                .messages(Collections.singletonList(ResponseMessageDto.builder()
                        .message("Запись обновлена '" + id + "'")
                        .build()))
                .build();
    }

    public static ResponseMessageDto createResponseMessagesDtoDelete(Long id) {
        return ResponseMessageDto.builder()
                .message(MessageBundleConfig.getMessage("employee.deleteRow", id))
                .build();
    }

    public static EmployeeDto createDataEmployeeDtoToInsert(Long id) {
        return EmployeeDto.builder()
                .id(id)
                .description("10L")
                .phone("90000000000")
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .position("DIRECTOR")
                .salary("1000000.0")
                .gender("M")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().toString())
                .birthday(LocalDate.now().toString())
                .build();
    }

    public static ResponseEmployeeMessagesDto createResponseEmployeeMessagesDtoInsert(Long numberRow) {
        return ResponseEmployeeMessagesDto.builder()
                .employeeDto(createDataEmployeeDtoToInsert(numberRow))
                .messages(Collections.singletonList(ResponseMessageDto.builder()
                        .message("Запись добавлена '" + numberRow + "'")
                        .build()))
                .build();
    }

    public static List<ResponseEmployeeMessagesDto> createResponseEmployeeMessagesDtoInsertList() {
        List<ResponseEmployeeMessagesDto> responseEmployeeMessagesDto = new ArrayList<>();
        for (EmployeeDto employeeDto : createDataEmployeeDtoListToInsert()) {
            responseEmployeeMessagesDto.add(createResponseEmployeeMessagesDtoInsert(employeeDto.getId()));
        }
        return responseEmployeeMessagesDto;
    }

    public static List<EmployeeDto> createDataEmployeeDtoListToInsert() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(createDataEmployeeDtoToInsert(8L));
        employeeDtoList.add(createDataEmployeeDtoToInsert(9L));
        employeeDtoList.add(createDataEmployeeDtoToInsert(10L));
        return employeeDtoList;
    }
}
