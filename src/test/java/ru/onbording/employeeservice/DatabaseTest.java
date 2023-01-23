package ru.onbording.employeeservice;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
//@Testcontainers
@ContextConfiguration(initializers = DatabaseTest.DataSourceInitializer.class)
@TestPropertySource("/application.properties")
public class DatabaseTest {
    @BeforeAll
    static void init() {
        database.start();
    }
    //@Container
    private static final PostgreSQLContainer<?> database =
            new PostgreSQLContainer<>("postgres:14")
                    .withInitScript("init_script.sql") //todo лучше скрипт использовать не при создании контейнера, а перед запуском тестов (над классами например) с помощью аннотации @Sql
                    .waitingFor(Wait.forListeningPort()); //todo не думаю что эта настройка прям нужна)

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    //"spring.test.database.replace=none",
                    "spring.datasource.driver-class-name=org.postgresql.Driver",
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword()
            );
        }
    }
}
