package com.pablom.imatiaorders.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pablom.imatiaorders.repository.entity.OrderTrackingEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ExtendWith(SpringExtension.class)
@EnableMongoRepositories(basePackageClasses = {OrderTrackingRepository.class})
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Testcontainers(disabledWithoutDocker = true)
class OrderTrackingRepositoryTest {

    private static final String DATABASE_NAME = "test_imatia";

    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.
            parse("mongo:4.0.10"));

    @Autowired
    private OrderTrackingRepository repository;

    @BeforeAll
    static void beforeAll() {
        MONGO_DB_CONTAINER.start();
    }

    @AfterAll
    static void afterAll() {
        MONGO_DB_CONTAINER.stop();
    }

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @TestConfiguration
    @SuppressWarnings("PackageVisibleInnerClass")
    static class MongoTestConfiguration {

        @Bean
        public MongoClient mongo() {
            ConnectionString connectionString = new ConnectionString(
                    MONGO_DB_CONTAINER.getReplicaSetUrl(DATABASE_NAME));
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

            return MongoClients.create(mongoClientSettings);
        }

        @Bean
        public MongoTemplate mongoTemplate() throws Exception {
            return new MongoTemplate(mongo(), "test_imatia");
        }
    }


    @Test
    void shouldSaveAndGetOrderTracking() {
        // Given
        Instant instant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        OrderTrackingEntity orderTrackingEntity = OrderTrackingEntity.builder()
                .orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(instant)
                .build();

        // When
        OrderTrackingEntity save = repository.save(orderTrackingEntity);
        OrderTrackingEntity query = repository.findById(orderTrackingEntity.getOrderId())
                .orElse(OrderTrackingEntity.builder().build());

        // Then
        Assertions.assertThat(save).isEqualTo(query);
    }

    @Test
    void shouldUpdateAndGetOrderTracking() {
        // Given
        Instant instant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        OrderTrackingEntity orderTrackingEntity = OrderTrackingEntity.builder()
                .orderId(new BigDecimal(1))
                .trackingStatusId(1)
                .changeStatusDate(instant)
                .build();

        OrderTrackingEntity orderTrackingEntityUpdate = OrderTrackingEntity.builder()
                .orderId(new BigDecimal(1))
                .trackingStatusId(2)
                .changeStatusDate(instant)
                .changeStatusDate(instant)
                .build();

        // When
        repository.save(orderTrackingEntity);

        OrderTrackingEntity update = repository.save(orderTrackingEntityUpdate);
        OrderTrackingEntity query = repository.findById(orderTrackingEntity.getOrderId())
                .orElse(OrderTrackingEntity.builder().build());

        // Then
        Assertions.assertThat(update).isEqualTo(query);
    }
}