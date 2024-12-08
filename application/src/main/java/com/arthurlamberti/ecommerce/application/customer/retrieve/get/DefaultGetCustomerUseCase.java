package com.arthurlamberti.ecommerce.application.customer.retrieve.get;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;

public class DefaultGetCustomerUseCase extends GetCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerUseCase(
            final CustomerGateway customerGateway
    ) {
        this.customerGateway = customerGateway;
    }

    @Override
    public GetCustomerOutput execute(String customerId) {
        return this.customerGateway.findById(CustomerID.from(customerId))
                .map(GetCustomerOutput::from)
                .orElseThrow(() -> NotFoundException.with(Address.class, CustomerID.from(customerId)));
    }
}
