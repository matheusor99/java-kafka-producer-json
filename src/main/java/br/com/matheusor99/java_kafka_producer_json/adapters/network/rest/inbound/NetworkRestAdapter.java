package br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;
import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.resultado.ResultadoRetorno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/products")
public interface NetworkRestAdapter {

    @PostMapping("/register")
    ResponseEntity<ResultadoRetorno> registerProduct(@RequestBody ProductDto product);
}
