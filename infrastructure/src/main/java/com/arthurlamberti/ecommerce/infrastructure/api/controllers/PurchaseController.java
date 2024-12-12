package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.purchase.create.CreatePurchaseCommand;
import com.arthurlamberti.ecommerce.application.purchase.create.CreatePurchaseUseCase;
import com.arthurlamberti.ecommerce.infrastructure.api.PurchaseAPI;
import com.arthurlamberti.ecommerce.infrastructure.purchase.models.CreatePurchaseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PurchaseController implements PurchaseAPI {

    private final CreatePurchaseUseCase createPurchaseUseCase;

    public PurchaseController(CreatePurchaseUseCase createPurchaseUseCase) {
        this.createPurchaseUseCase = createPurchaseUseCase;
    }

    @Override
    public ResponseEntity<?> createPurchase(CreatePurchaseRequest input) {
        final var aCommand = CreatePurchaseCommand.with(
                input.sellerId(),
                input.customerId(),
                input.itemsId(),
                input.totalValue(),
                input.totalQty(),
                input.addressId()
        );
        final var output = this.createPurchaseUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/purchases/" + output.id())).body(output);
    }
}
