package com.arthurlamberti.ecommerce.infrastructure.customer;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerMySQLGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    public CustomerMySQLGateway(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(CustomerJPAEntity.from(customer))
                .toAggregate();
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll()
                .stream()
                .map(CustomerJPAEntity::toAggregate)
                .toList();
    }

    @Override
    public Optional<Customer> findById(CustomerID from) {
        return this.customerRepository.findById(from.getValue())
                .map(CustomerJPAEntity::toAggregate);
    }
}
