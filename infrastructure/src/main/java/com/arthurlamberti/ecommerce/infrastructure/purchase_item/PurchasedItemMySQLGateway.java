package com.arthurlamberti.ecommerce.infrastructure.purchase_item;

import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItemGateway;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemRepository;
import com.arthurlamberti.ecommerce.infrastructure.purchase.persistence.PurchaseRepository;
import com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence.PurchasedItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence.PurchasedItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchasedItemMySQLGateway implements PurchasedItemGateway {

    private final PurchasedItemRepository purchasedItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final ItemRepository itemRepository;

    public PurchasedItemMySQLGateway(final PurchasedItemRepository purchasedItemRepository, PurchaseRepository purchaseRepository, ItemRepository itemRepository) {
        this.purchasedItemRepository = purchasedItemRepository;
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Boolean create(List<PurchasedItem> purchasedItems) {
        final var purchase = purchaseRepository.findById(purchasedItems.get(0).getPurchaseId()).orElse(null);
        final var entities = purchasedItems
                .stream()
                .map(purchasedItem -> {
                    var item = itemRepository.findById(purchasedItem.getItemId()).orElse(null);
                    return PurchasedItemJpaEntity.from(purchasedItem, purchase, item);
                })
                .toList();
        this.purchasedItemRepository.saveAll(entities);
        return true;
    }
}
