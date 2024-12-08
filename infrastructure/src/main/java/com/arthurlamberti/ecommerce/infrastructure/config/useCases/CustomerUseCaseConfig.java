package com.arthurlamberti.ecommerce.infrastructure.config.useCases;

import com.arthurlamberti.ecommerce.application.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.create.DefaultCreateCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.retrieve.get.DefaultGetCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.retrieve.get.GetCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.retrieve.list.DefaultListCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.retrieve.list.ListCustomerUseCase;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    public CustomerUseCaseConfig(
            final CustomerGateway customerGateway
    ) {
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase() {
        return new DefaultCreateCustomerUseCase(customerGateway);
    }

    @Bean
    public ListCustomerUseCase listCustomerUseCase() {
        return new DefaultListCustomerUseCase(customerGateway);
    }

    @Bean
    public GetCustomerUseCase getCustomerUseCase() {
        return new DefaultGetCustomerUseCase(customerGateway);
    }
}
