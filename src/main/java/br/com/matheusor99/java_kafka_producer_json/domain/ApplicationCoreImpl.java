package br.com.matheusor99.java_kafka_producer_json.domain;

import br.com.matheusor99.java_kafka_producer_json.adapters.exceptions.ProducerProductException;
import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;
import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.resultado.ResultadoRetorno;
import br.com.matheusor99.java_kafka_producer_json.domain.event.Product;
import br.com.matheusor99.java_kafka_producer_json.domain.rules.validations.ValidationDescription;
import br.com.matheusor99.java_kafka_producer_json.domain.rules.validations.ValidationName;
import br.com.matheusor99.java_kafka_producer_json.domain.rules.validations.chain.ValidationProductChain;
import br.com.matheusor99.java_kafka_producer_json.ports.inbound.NetworkRestInboundPort;
import br.com.matheusor99.java_kafka_producer_json.ports.outbound.MessageBrokerProducerOutbound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ApplicationCoreImpl implements NetworkRestInboundPort {

    private final MessageBrokerProducerOutbound messageBrokerProducer;

    @Autowired
    public ApplicationCoreImpl(MessageBrokerProducerOutbound messageBrokerProducer) {
        this.messageBrokerProducer = messageBrokerProducer;
    }

    @Override
    public ResultadoRetorno registerProduct(final ProductDto product) {
        final long initProcess = System.currentTimeMillis();

        try {
            final ValidationProductChain validationChain = new ValidationName()
                    .next(new ValidationDescription());

            if (!validationChain.validateProduct(product)) return new ResultadoRetorno(null, "Invalid product", false);

            var productJson = Product.getInstance(product);
            var key = UUID.randomUUID().toString();

            messageBrokerProducer.sendMessageProduct(key, productJson);

            log.info("Product registration request sent uuid: {}", key);

            return new ResultadoRetorno(key, "Product registration request sent", true);
        } catch (ProducerProductException e) {
            log.info("Error processing product: {}", e.getMessage());
            return new ResultadoRetorno(null, "Error processing product", false);
        } finally {
            log.info("Processing time: {} ms", System.currentTimeMillis() - initProcess);
        }
    }
}
