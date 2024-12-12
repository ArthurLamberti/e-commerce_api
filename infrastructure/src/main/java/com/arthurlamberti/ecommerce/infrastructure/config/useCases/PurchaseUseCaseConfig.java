package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.purchase.create.CreatePurchaseUseCase;
import com.arthurlamberti.ecommerce.application.purchase.create.DefaultCreatePurchaseUseCase;
import com.arthurlamberti.ecommerce.application.purchased_item.create.CreatePurchasedItemUseCase;
import com.arthurlamberti.ecommerce.application.shipping.create_by_purchase.CreateShippingUseCase;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseUseCaseConfig {

    private final PurchaseGateway purchaseGateway;
    private final SellerGateway sellerGateway;
    private final CustomerGateway customerGateway;
    private final ItemGateway itemGateway;
    private final AddressGateway addressGateway;

    private final CreateShippingUseCase createShippingUseCase;
    private final CreatePurchasedItemUseCase createPurchasedItemUseCase;

    public PurchaseUseCaseConfig(PurchaseGateway purchaseGateway, SellerGateway sellerGateway, CustomerGateway customerGateway, ItemGateway itemGateway, AddressGateway addressGateway, CreateShippingUseCase createShippingUseCase, CreatePurchasedItemUseCase createPurchasedItemUseCase) {
        this.purchaseGateway = purchaseGateway;
        this.sellerGateway = sellerGateway;
        this.customerGateway = customerGateway;
        this.itemGateway = itemGateway;
        this.addressGateway = addressGateway;
        this.createShippingUseCase = createShippingUseCase;
        this.createPurchasedItemUseCase = createPurchasedItemUseCase;
    }

    @Bean
    public CreatePurchaseUseCase createPurchaseUseCase(){
        return new DefaultCreatePurchaseUseCase(purchaseGateway,sellerGateway, customerGateway, addressGateway, itemGateway, createShippingUseCase, createPurchasedItemUseCase);
    }

}
