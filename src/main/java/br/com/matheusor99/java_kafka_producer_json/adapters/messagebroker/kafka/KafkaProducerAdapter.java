package br.com.matheusor99.java_kafka_producer_json.adapters.messagebroker.kafka;

import br.com.matheusor99.java_kafka_producer_json.adapters.exceptions.ProducerProductException;
import br.com.matheusor99.java_kafka_producer_json.domain.event.Product;
import br.com.matheusor99.java_kafka_producer_json.ports.outbound.MessageBrokerProducerOutbound;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerAdapter implements MessageBrokerProducerOutbound {
    private final KafkaProducer<String, Product> producer;

    @Value("${kafka.topic-product}")
    private String topicProduct;

    @Autowired
    public KafkaProducerAdapter(KafkaProducer<String, Product> producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessageProduct(final String key, final Product productJson) {

        producer.send(new ProducerRecord<>(topicProduct, key, productJson), (metadata, exception) -> {
            if (exception != null) {
                throw new ProducerProductException("Error sending product: " + exception.getMessage());
            } else {
                log.info("Product sent successfully key: {}", key);
            }
        });

        producer.flush();

    }
}
