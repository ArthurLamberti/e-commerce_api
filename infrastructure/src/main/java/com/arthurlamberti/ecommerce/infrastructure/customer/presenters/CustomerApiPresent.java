package com.arthurlamberti.ecommerce.infrastructure.customer.presenters;

import com.arthurlamberti.ecommerce.application.customer.retrieve.get.GetCustomerOutput;
import com.arthurlamberti.ecommerce.application.customer.retrieve.list.ListCustomerOutput;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.AddressSummaryResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CustomerListResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.GetCustomerResponse;

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

    static GetCustomerResponse present(GetCustomerOutput output) {
        final var addresses = output.address()
                .stream()
                .map(AddressSummaryResponse::from)
                .toList();
        return new GetCustomerResponse(
                output.name(),
                output.email(),
                output.document(),
                output.active(),
                addresses,
                output.createdAt(),
                output.deletedAt()
        );
    }
}
