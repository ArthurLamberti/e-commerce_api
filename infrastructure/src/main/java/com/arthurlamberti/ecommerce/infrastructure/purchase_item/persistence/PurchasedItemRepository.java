package com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItemJpaEntity, String> {
}
