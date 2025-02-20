package br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto;

import lombok.extern.jackson.Jacksonized;

public record ProductDto(String name, String description, Double price) {
}
