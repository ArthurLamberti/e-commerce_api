package com.arthurlamberti.ecommerce.infrastructure.purchase.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseJpaEntity, String> {
}
