package com.arthurlamberti.ecommerce.infrastructure.shipping.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingJpaEntity, String> {
}
