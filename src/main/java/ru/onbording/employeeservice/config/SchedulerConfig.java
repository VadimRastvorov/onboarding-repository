package ru.onbording.employeeservice.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.onbording.employeeservice.service.EmployeeService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulerConfig {
    @Autowired
    private final EmployeeService employeeService;

    @Scheduled(cron = "${bot.recountNewDeleteEmployeeCron}")
    public void deleteEmployee() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Запуск шедулера удаления записи из БД");
        //employeeService.deleteOneRowEmployee();
        LocalDateTime end = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД. Время выполнения в секундах: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
