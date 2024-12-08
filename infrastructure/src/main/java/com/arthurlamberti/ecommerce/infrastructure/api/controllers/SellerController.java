package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.seller.create.CreateSellerCommand;
import com.arthurlamberti.ecommerce.application.seller.create.CreateSellerUseCase;
import com.arthurlamberti.ecommerce.application.seller.retrieve.get.GetSellerUseCase;
import com.arthurlamberti.ecommerce.application.seller.retrieve.list.ListSellerUseCase;
import com.arthurlamberti.ecommerce.infrastructure.api.SellerAPI;
import com.arthurlamberti.ecommerce.infrastructure.seller.models.CreateSellerRequest;
import com.arthurlamberti.ecommerce.infrastructure.seller.models.GetSellerResponse;
import com.arthurlamberti.ecommerce.infrastructure.seller.models.SellerListResponse;
import com.arthurlamberti.ecommerce.infrastructure.seller.presenters.SellerApiPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class SellerController implements SellerAPI {

    private final CreateSellerUseCase createSellerUseCase;
    private final ListSellerUseCase listSellerUseCase;
    private final GetSellerUseCase getSellerUseCase;

    public SellerController(final CreateSellerUseCase createSellerUseCase,
                            final ListSellerUseCase listSellerUseCase,
                            final GetSellerUseCase getSellerUseCase) {
        this.createSellerUseCase = createSellerUseCase;
        this.listSellerUseCase = listSellerUseCase;
        this.getSellerUseCase = getSellerUseCase;
    }


    @Override
    public ResponseEntity<?> createSeller(CreateSellerRequest input) {
        final var aCommand = CreateSellerCommand.with(
                input.name(),
                input.email(),
                input.description(),
                input.document()
        );

        final var output = this.createSellerUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/sellers/" + output.id())).body(output);
    }

    @Override
    public List<SellerListResponse> list() {
        return this.listSellerUseCase.execute()
                .stream()
                .map(SellerApiPresent::present)
                .toList();
    }

    @Override
    public GetSellerResponse getById(String customerId) {
        return SellerApiPresent.present(this.getSellerUseCase.execute(customerId));
    }
}
