package ru.onbording.task1.configuration;

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
public class DeleteEmployeeSchedulerJob {
    private final DeleteEmployeeSchedulerService deleteEmployeeScheduler;

    @Autowired
    public DeleteEmployeeSchedulerJob(DeleteEmployeeSchedulerService deleteEmployeeScheduler) {
        this.deleteEmployeeScheduler = deleteEmployeeScheduler;
    }

    @Scheduled(cron = "1 * * * * *")
    public void deleteEmployee() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Find new delete job started.");
        deleteEmployeeScheduler.deleteEmployee();

        LocalDateTime end = LocalDateTime.now();
        log.info("Find new delete job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
