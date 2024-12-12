package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.purchased_item.create.CreatePurchasedItemUseCase;
import com.arthurlamberti.ecommerce.application.purchased_item.create.DefaultCreatePurchasedItemUseCase;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItemGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchasedItemUseCaseConfig {

    private final PurchasedItemGateway purchasedItemGateway;

    public PurchasedItemUseCaseConfig(final PurchasedItemGateway purchasedItemGateway) {
        this.purchasedItemGateway = purchasedItemGateway;
    }

    @Bean
    public CreatePurchasedItemUseCase createPurchasedItemUseCase() {
        return new DefaultCreatePurchasedItemUseCase(purchasedItemGateway);
    }
}
