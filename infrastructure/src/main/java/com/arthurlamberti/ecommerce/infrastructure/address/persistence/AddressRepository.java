package com.arthurlamberti.ecommerce.infrastructure.address.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressJPAEntity, String> {
    Page<AddressJPAEntity> findAll(Specification<AddressJPAEntity> whereClause, Pageable page);
}
