package ru.onbording.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //todo эту аннотацию перенеси в шедулер
public class Task1Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1Application.class, args);
    }
}
