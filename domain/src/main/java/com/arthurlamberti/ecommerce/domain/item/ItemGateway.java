package com.arthurlamberti.ecommerce.domain.item;

import java.util.List;
import java.util.Optional;

public interface ItemGateway {

    Item create(final Item anItem);

    Optional<Item> findById(final ItemID anId);

    List<Item> findAll();

}
