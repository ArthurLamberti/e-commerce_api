package com.arthurlamberti.ecommerce.application.customer.retrieve.list;

import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;

import java.util.List;

public class DefaultListCustomerUseCase extends ListCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public List<ListCustomerOutput> execute() {
        return this.customerGateway.findAll()
                .stream()
                .map(ListCustomerOutput::from)
                .toList();
    }
}
