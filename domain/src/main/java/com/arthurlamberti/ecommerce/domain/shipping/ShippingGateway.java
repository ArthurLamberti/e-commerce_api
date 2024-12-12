package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.enums.ShippingStatus;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemID;

import java.util.List;
import java.util.Optional;

public interface ShippingGateway {

    Shipping create(final Shipping aShipping);

    Optional<Shipping> findById(final Shipping anId);

    Shipping updateStatus(final Shipping shipping, final ShippingStatus status);
}
