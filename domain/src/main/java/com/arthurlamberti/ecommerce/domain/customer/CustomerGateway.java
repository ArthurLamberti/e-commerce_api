package com.arthurlamberti.ecommerce.domain.customer;

import java.util.Collection;
import java.util.List;

public interface CustomerGateway {
    Customer create(final Customer customer);

    List<Customer> findAll();
}
