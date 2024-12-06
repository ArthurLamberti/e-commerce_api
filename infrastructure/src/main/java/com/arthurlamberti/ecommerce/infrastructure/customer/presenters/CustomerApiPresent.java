package com.arthurlamberti.ecommerce.infrastructure.customer.presenters;

import com.arthurlamberti.ecommerce.application.customer.retrieve.list.ListCustomerOutput;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CustomerListResponse;

public interface CustomerApiPresent {

    static CustomerListResponse present(ListCustomerOutput listCustomerOutput) {
        return new CustomerListResponse(
                listCustomerOutput.name(),
                listCustomerOutput.email(),
                listCustomerOutput.document(),
                listCustomerOutput.active(),
                listCustomerOutput.createdAt(),
                listCustomerOutput.deletedAt()
        );
    }
}
