package br.com.matheusor99.java_kafka_producer_json.ports.outbound;

import br.com.matheusor99.java_kafka_producer_json.domain.event.Product;

public interface MessageBrokerProducerOutbound {
    void sendMessageProduct(String key, Product productJson);
}
