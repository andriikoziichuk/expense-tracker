package ua.andrew1903.expensetracker.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.topics")
public record Topics(
        String expenseTrackerTopic
) {
}
