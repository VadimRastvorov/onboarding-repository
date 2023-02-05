package ru.onbording.employeeservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.onbording.employeeservice.InitializerTest;
import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.data.EmployeeData;
import ru.onbording.employeeservice.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
public class EmployeeValidationServiceTest extends InitializerTest {
    @Autowired
    EmployeeValidationService employeeValidationService;

    @Test
    void testCheckDataValidCheck() {
        List<String> messages = employeeValidationService.checkData(EmployeeData.createDataEmployeeDto());
        assertThat(messages.size()).isEqualTo(0);
    }

    @Test
    @Sql({"/db/delete_tables.sql", "/db/insert_employees.sql", "/db/insert_tasks.sql"})
    void testCheckDataNoValidRequiredData() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(5L)
                .description("description_1")
                .phone("90000000000")
                .middleName("middleName")
                .endDate(LocalDate.now().toString())
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "gender"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "position"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "salary"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "lastName"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "firstName"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "birthday"));
        messages.add(MessageBundleConfig.getMessage("employee.checkRequiredData", "startDate"));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidSalary() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .description("description_1")
                .phone("77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("DIRECTOR")
                .salary("100001.0")
                .gender("M")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.salary", employeeDto.getSalary(), employeeDto.getPosition()));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidPosition() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .description("description_1")
                .phone("77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("WORKER")
                .salary("100001.0")
                .gender("M")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.validPosition", employeeDto.getPosition()));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidMaxTasks() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .description("description_1")
                .phone("77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("MANAGER")
                .salary("200001.0")
                .gender("M")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("task.taskCount", employeeDto.getId()));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidGender() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(5L)
                .description("description_1")
                .phone("77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("MANAGER")
                .salary("200001.0")
                .gender("A")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.validGender"));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidPhone() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(5L)
                .description("description_1")
                .phone("+77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("MANAGER")
                .salary("200001.0")
                .gender("F")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.validPhone", employeeDto.getPhone()));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataNoValidWorkPeriod() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(5L)
                .description("description_1")
                .phone("77771111111")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("MANAGER")
                .salary("200001.0")
                .gender("F")
                .startDate("2023-01-01")
                .endDate("2001-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.endDate"));
        assertThat(messagesEmployee).isEqualTo(messages);
    }

    @Test
    void testCheckDataPhoneDirector() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(5L)
                .description("description_1")
                .firstName("Сидор")
                .lastName("Сидоров")
                .middleName("Сидрович")
                .position("DIRECTOR")
                .salary("700001.0")
                .gender("F")
                .startDate("2001-01-01")
                .endDate("2023-01-01")
                .birthday("1984-11-12")
                .build();
        List<String> messagesEmployee = employeeValidationService.checkData(employeeDto);
        List<String> messages = new ArrayList<>();
        messages.add(MessageBundleConfig
                .getMessage("employee.phoneDirector", employeeDto.getPosition(), "phone"));
        assertThat(messagesEmployee).isEqualTo(messages);
    }
}
