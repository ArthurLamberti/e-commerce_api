package com.arthurlamberti.ecommerce.infrastructure.item;

import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemID;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemRepository;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemMySQLGateway implements ItemGateway {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;


    public ItemMySQLGateway(final ItemRepository itemRepository, SellerRepository sellerRepository) {
        this.itemRepository = itemRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Item create(Item anItem) {
        final var seller = sellerRepository.findById(anItem.getSellerId()).orElse(null);
        return this.itemRepository.save(ItemJpaEntity.from(anItem,seller))
                .toAggregate();
    }

    @Override
    public Optional<Item> findById(ItemID anId) {
        return this.itemRepository.findById(anId.getValue())
                .map(ItemJpaEntity::toAggregate);
    }

    @Override
    public List<Item> findAll() {
        return null;
    }
}
