package ru.onbording.task1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.onbording.task1.service.DeleteEmployeeSchedulerService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Configuration
@EnableScheduling
public class DeleteEmployeeSchedulerConfig {
    private final DeleteEmployeeSchedulerService deleteEmployeeSchedulerService;

    @Autowired
    public DeleteEmployeeSchedulerConfig(DeleteEmployeeSchedulerService deleteEmployeeSchedulerService) {
        this.deleteEmployeeSchedulerService = deleteEmployeeSchedulerService;
    }

    @Scheduled(cron = "1 * * * * *")
    public void deleteEmployee() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД");
        deleteEmployeeSchedulerService.deleteEmployee();

        LocalDateTime end = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД. Время выполнения в секундах: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
