package com.arthurlamberti.ecommerce.infrastructure.seller;

import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SellerMySQLGateway implements SellerGateway {

    private final SellerRepository sellerRepository;

    public SellerMySQLGateway(final SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller create(Seller seller) {
        return this.sellerRepository.save(SellerJPAEntity.from(seller)).toAggregate();
    }

    @Override
    public List<Seller> findAll() {
        return this.sellerRepository.findAll()
                .stream()
                .map(SellerJPAEntity::toAggregate)
                .toList();
    }

    @Override
    public Optional<Seller> findById(SellerID from) {
        return this.sellerRepository.findById(from.getValue())
                .map(SellerJPAEntity::toAggregate);
    }
}
