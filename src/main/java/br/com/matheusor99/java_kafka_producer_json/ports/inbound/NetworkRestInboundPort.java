package br.com.matheusor99.java_kafka_producer_json.ports.inbound;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;
import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.resultado.ResultadoRetorno;

public interface NetworkRestInboundPort {
    ResultadoRetorno registerProduct(ProductDto product);
}
