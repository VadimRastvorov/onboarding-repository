package ru.onbording.task1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.onbording.task1.service.EmployeeService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfig {
    private final EmployeeService employeeService;

    @Autowired
    public SchedulerConfig(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "${bot.recountNewDeleteFixedRate}")
    public void deleteEmployee() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД"); //todo исправить текст
        //employeeService.deleteOneEmployee();
        LocalDateTime end = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД. Время выполнения в секундах: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
