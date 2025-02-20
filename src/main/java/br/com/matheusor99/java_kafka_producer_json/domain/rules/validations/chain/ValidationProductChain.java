package br.com.matheusor99.java_kafka_producer_json.domain.rules.validations.chain;

import br.com.matheusor99.java_kafka_producer_json.adapters.network.rest.inbound.dto.ProductDto;

public abstract class ValidationProductChain {
    private ValidationProductChain next;


    public ValidationProductChain next(ValidationProductChain next) {
        this.next = next;
        return next;
    }

    public boolean validateProduct(ProductDto product) {
        if (!doValidate(product)) return false;

        return next == null || next.validateProduct(product);
    }

    protected abstract boolean doValidate(ProductDto product);
}
