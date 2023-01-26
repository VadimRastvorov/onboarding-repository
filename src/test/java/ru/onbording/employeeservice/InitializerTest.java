package ru.onbording.employeeservice;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ContextConfiguration(initializers = InitializerTest.DataSourceInitializer.class)
@TestPropertySource("/application.properties")
public class InitializerTest {
    @BeforeAll
    static void init() {
        database.start();
        zookeeper.start();
        kafka.start();
    }

    private static final PostgreSQLContainer<?> database =
            new PostgreSQLContainer<>("postgres:latest");

    private static final DockerImageName KAFKA_TEST_IMAGE = DockerImageName.parse("confluentinc/cp-kafka:latest");

    private static final DockerImageName ZOOKEEPER_TEST_IMAGE = DockerImageName.parse(
            "confluentinc/cp-zookeeper:latest"
    );
    private static final Network network = Network.newNetwork();

    private static final KafkaContainer kafka = new KafkaContainer(KAFKA_TEST_IMAGE)
            .withNetwork(network)
            .withExternalZookeeper("zookeeper:2181");

    private static final GenericContainer<?> zookeeper = new GenericContainer<>(ZOOKEEPER_TEST_IMAGE)
            .withNetwork(network)
            .withNetworkAliases("zookeeper")
            .withEnv("ZOOKEEPER_CLIENT_PORT", "2181");

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.driver-class-name=org.postgresql.Driver",
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword(),
                    "spring.kafka.producer.bootstrap-servers=" + kafka.getBootstrapServers()
            );
        }
    }
}
