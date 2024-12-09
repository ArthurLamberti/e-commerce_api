package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.item.create.CreateItemUseCase;
import com.arthurlamberti.ecommerce.application.item.create.DefaultCreateItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.get.DefaultGetItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.get.GetItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.list.DefaultListItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.list.ListItemUseCase;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemUseCaseConfig {

    private final ItemGateway itemGateway;
    private final SellerGateway sellerGateway;

    public ItemUseCaseConfig(
            final ItemGateway itemGateway,
            final SellerGateway sellerGateway
    ) {
        this.itemGateway = itemGateway;
        this.sellerGateway = sellerGateway;
    }

    @Bean
    public CreateItemUseCase createItemUseCase() {
        return new DefaultCreateItemUseCase(itemGateway, sellerGateway);
    }

    @Bean
    public GetItemUseCase getItemUseCase() {
        return new DefaultGetItemUseCase(itemGateway);
    }

    @Bean
    public ListItemUseCase listItemUseCase() {
        return new DefaultListItemUseCase(itemGateway);
    }

}
