package com.arthurlamberti.ecommerce.infrastructure.seller.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerJPAEntity, String> {
}
