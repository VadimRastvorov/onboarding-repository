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
@Configuration //todo это скорее конфигурация // done
@EnableScheduling //todo эту аннотацию перенеси в шедулер //done
public class SchedulerConfig {
    private final EmployeeService employeeService;

    @Autowired
    public SchedulerConfig(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "${bot.recountNewDeleteFixedRate}")
    //todo по ТЗ необходимо использовать cron и удалять каждую 1 минуту по записи //done
    public void deleteEmployee() { //todo название метода не соответствует тому, что в нём происходит //done
        LocalDateTime start = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД");
        //employeeService.deleteOneEmployee();
        LocalDateTime end = LocalDateTime.now();
        log.info("Завершение шедулера удаления записи из БД. Время выполнения в секундах: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
