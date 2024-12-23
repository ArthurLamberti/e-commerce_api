package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.address.create.CreateAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.create.DefaultCreateAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.delete.DefaultDeleteAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.delete.DeleteAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.get.DefaultGetAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.get.GetAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.list.DefaultListAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.list.ListAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.update.DefaultUpdateAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.update.UpdateAddressUsecase;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressUseCaseConfig {

    private final AddressGateway addressGateway;
    private final CustomerGateway customerGateway;
    private final SellerGateway sellerGateway;

    public AddressUseCaseConfig(
            final AddressGateway addressGateway,
            final CustomerGateway customerGateway,
            final SellerGateway sellerGateway) {
        this.addressGateway = addressGateway;
        this.customerGateway = customerGateway;
        this.sellerGateway = sellerGateway;
    }

    @Bean
    public CreateAddressUsecase createAddressUsecase() {
        return new DefaultCreateAddressUsecase(addressGateway, customerGateway, sellerGateway);
    }

    @Bean
    public ListAddressUsecase listAddressUsecase() {
        return new DefaultListAddressUsecase(addressGateway);
    }

    @Bean
    public GetAddressUsecase getAddressUsecase() {
        return new DefaultGetAddressUsecase(addressGateway);
    }

    @Bean
    public DeleteAddressUsecase deleteAddressUsecase() {
        return new DefaultDeleteAddressUsecase(addressGateway);
    }

    @Bean
    public UpdateAddressUsecase updateAddressUsecase() {
        return new DefaultUpdateAddressUsecase(addressGateway);
    }

}
