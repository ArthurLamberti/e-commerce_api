package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.customer.create.CreateCustomerCommand;
import com.arthurlamberti.ecommerce.application.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.ecommerce.application.customer.retrieve.list.ListCustomerUseCase;
import com.arthurlamberti.ecommerce.infrastructure.api.CustomerAPI;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CreateCustomerRequest;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CustomerListResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.presenters.CustomerApiPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomerUseCase listCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final ListCustomerUseCase listCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.listCustomerUseCase = listCustomerUseCase;
    }

    @Override
    public ResponseEntity<?> createCustomer(CreateCustomerRequest input) {
        final var aCommand = CreateCustomerCommand.with(input.name(), input.email(), input.document());

        final var output = this.createCustomerUseCase.execute(aCommand);
        return ResponseEntity.created(URI.create("/customers/" + output.id())).body(output);
    }

    @Override
    public List<CustomerListResponse> list() {
        return this.listCustomerUseCase.execute()
                .stream()
                .map(CustomerApiPresent::present)
                .toList();
    }
}
