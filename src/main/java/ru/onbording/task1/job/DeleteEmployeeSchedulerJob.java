package ru.onbording.task1.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.onbording.task1.service.DeleteEmployeeScheduler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class DeleteEmployeeSchedulerJob {
    private final DeleteEmployeeScheduler deleteEmployeeScheduler;
    @Autowired
    public DeleteEmployeeSchedulerJob(DeleteEmployeeScheduler deleteEmployeeScheduler) {
        this.deleteEmployeeScheduler = deleteEmployeeScheduler;
    }
    @Scheduled(fixedRateString = "${bot.recountNewDeleteFixedRate}")
    public void findNewArticles() {
        LocalDateTime start = LocalDateTime.now();
        log.info("Find new delete job started.");
        deleteEmployeeScheduler.deleteEmployee();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new delete job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
