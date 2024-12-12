package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.shipping.create_by_purchase.CreateShippingUseCase;
import com.arthurlamberti.ecommerce.application.shipping.create_by_purchase.DefaultCreateShippingUseCase;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.shipping.ShippingGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingUseCaseConfig {

    private final ShippingGateway shippingGateway;
    private final AddressGateway addressGateway;
    private final PurchaseGateway purchaseGateway;


    public ShippingUseCaseConfig(ShippingGateway shippingGateway, AddressGateway addressGateway, PurchaseGateway purchaseGateway) {
        this.shippingGateway = shippingGateway;
        this.addressGateway = addressGateway;
        this.purchaseGateway = purchaseGateway;
    }

    @Bean
    public CreateShippingUseCase createShippingUseCase() {
        return new DefaultCreateShippingUseCase(shippingGateway, addressGateway, purchaseGateway);
    }
}
