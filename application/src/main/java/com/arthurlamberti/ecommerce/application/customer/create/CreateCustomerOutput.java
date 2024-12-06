package com.arthurlamberti.ecommerce.application.customer.create;

import com.arthurlamberti.ecommerce.domain.customer.Customer;

public record CreateCustomerOutput(String id) {
    public static CreateCustomerOutput from(Customer customer) {
        return new CreateCustomerOutput(customer.getId().getValue());
    }
}
