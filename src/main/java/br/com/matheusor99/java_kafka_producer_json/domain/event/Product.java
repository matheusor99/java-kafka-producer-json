package br.com.matheusor99.java_kafka_producer_json.domain.event;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;

public record Product(String name, String description, Double price) {
    public static Product getInstance(final ProductDto product) {
        return new Product(product.name(), product.description(), product.price());
    }
}
