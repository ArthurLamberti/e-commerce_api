package com.arthurlamberti.ecommerce.infrastructure.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJPAEntity, String> {
}
