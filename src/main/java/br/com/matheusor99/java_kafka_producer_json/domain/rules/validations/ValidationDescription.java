package br.com.matheusor99.java_kafka_producer_json.domain.rules.validations;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;
import br.com.matheusor99.java_kafka_producer_json.domain.rules.validations.chain.ValidationProductChain;
import io.micrometer.common.util.StringUtils;

public class ValidationDescription extends ValidationProductChain {

    @Override
    protected boolean doValidate(ProductDto product) {
        return StringUtils.isNotBlank(product.description());
    }
}
