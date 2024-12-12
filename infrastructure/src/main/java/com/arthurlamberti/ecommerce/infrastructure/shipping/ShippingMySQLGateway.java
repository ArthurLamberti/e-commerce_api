package com.arthurlamberti.ecommerce.infrastructure.shipping;

import com.arthurlamberti.ecommerce.domain.enums.ShippingStatus;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.shipping.ShippingGateway;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressRepository;
import com.arthurlamberti.ecommerce.infrastructure.shipping.persistence.ShippingJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.shipping.persistence.ShippingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShippingMySQLGateway implements ShippingGateway {

    private final ShippingRepository shippingRepository;
    private final AddressRepository addressRepository;

    public ShippingMySQLGateway(ShippingRepository shippingRepository, AddressRepository addressRepository) {
        this.shippingRepository = shippingRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Shipping create(Shipping aShipping) {
        final var address = addressRepository.findById(aShipping.getAddressId()).orElse(null);
        return this.shippingRepository.save(ShippingJpaEntity.from(aShipping, address))
                .toAggregate();
    }

    @Override
    public Optional<Shipping> findById(Shipping anId) {
        return Optional.empty();
    }

    @Override
    public Shipping updateStatus(Shipping shipping, ShippingStatus status) {
        return null;
    }
}
