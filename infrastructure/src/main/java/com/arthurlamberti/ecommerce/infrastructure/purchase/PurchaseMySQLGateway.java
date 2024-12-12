package com.arthurlamberti.ecommerce.infrastructure.purchase;

import com.arthurlamberti.ecommerce.domain.purchase.Purchase;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseID;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerRepository;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemRepository;
import com.arthurlamberti.ecommerce.infrastructure.purchase.persistence.PurchaseJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.purchase.persistence.PurchaseRepository;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerRepository;
import com.arthurlamberti.ecommerce.infrastructure.shipping.persistence.ShippingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PurchaseMySQLGateway implements PurchaseGateway {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    private final ShippingRepository shippingRepository;

    public PurchaseMySQLGateway(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, ItemRepository itemRepository, SellerRepository sellerRepository, ShippingRepository shippingRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.sellerRepository = sellerRepository;
        this.shippingRepository = shippingRepository;
    }


    @Override
    public Purchase create(Purchase aPurchase, Shipping aShipping) {
        final var customer = customerRepository.findById(aPurchase.getCustomerId()).orElse(null);
        final var seller = sellerRepository.findById(aPurchase.getSellerId()).orElse(null);
        final var shipping = shippingRepository.findById(aShipping.getId().getValue()).orElse(null);
        final var items = aPurchase.getItems()
                .stream()
                .map(id -> itemRepository.findById(id).orElse(null))
                .toList();

        return this.purchaseRepository.save(
                PurchaseJpaEntity.from(aPurchase, customer, seller, items, shipping)
        ).toAggregate();
    }

    @Override
    public Optional<Purchase> findById(PurchaseID anId) {
        return Optional.empty();
    }
}
