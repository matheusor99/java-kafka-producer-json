package br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;
import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.resultado.ResultadoRetorno;
import br.com.matheusor99.java_kafka_producer_json.ports.inbound.NetworkRestInboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkRestAdapterImpl implements NetworkRestAdapter{

    private final NetworkRestInboundPort domain;

    @Autowired
    public NetworkRestAdapterImpl(final NetworkRestInboundPort domain) {
        this.domain = domain;
    }


    @Override
    public ResponseEntity<ResultadoRetorno> registerProduct(final ProductDto product) {
        ResultadoRetorno result = domain.registerProduct(product);

        return result.sucesso() ?
                ResponseEntity.status(HttpStatus.OK).body(result) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
