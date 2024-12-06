package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.seller.create.CreateSellerUseCase;
import com.arthurlamberti.ecommerce.application.seller.create.DefaultCreateSellerUseCase;
import com.arthurlamberti.ecommerce.application.seller.retrieve.list.DefaultListSellerUseCase;
import com.arthurlamberti.ecommerce.application.seller.retrieve.list.ListSellerUseCase;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellerUseCaseConfig {

    private final SellerGateway sellerGateway;

    public SellerUseCaseConfig(
            final SellerGateway sellerGateway
    ) {
        this.sellerGateway = sellerGateway;
    }

    @Bean
    public CreateSellerUseCase createSellerUseCase() {
        return new DefaultCreateSellerUseCase(sellerGateway);
    }

    @Bean
    public ListSellerUseCase listSellerUseCase(){
        return new DefaultListSellerUseCase(sellerGateway);
    }

}
