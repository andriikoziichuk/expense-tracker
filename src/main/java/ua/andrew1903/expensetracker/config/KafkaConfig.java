package ua.andrew1903.expensetracker.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.andrew1903.expensetracker.kafka.Topics;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final Topics topics;

    @Bean
    public NewTopic expenseTrackerTopic() {
        return new NewTopic(topics.expenseTrackerTopic(), 5, (short) 1);
    }
}
