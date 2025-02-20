package br.com.matheusor99.java_kafka_producer_json.adapters.messagebroker.kafka.config;

import br.com.matheusor99.java_kafka_producer_json.domain.event.Product;
import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.validation.annotation.Validated;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@Validated
@Order(1)
public class KafkaConfig {
    private String bootstrapServers;
    private String topicProduct;

    @Bean
    NewTopic createTopic() {
        return new NewTopic(topicProduct, 1, (short) 1);
    }

    @Bean
    Properties propertiesProducer() {
        var properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return properties;
    }

    @Bean
    KafkaProducer<String, Product> kafkaProducer() {
        return new KafkaProducer<>(propertiesProducer());
    }
}
