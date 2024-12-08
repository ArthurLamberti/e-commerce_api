package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.address.create.CreateAddressCommand;
import com.arthurlamberti.ecommerce.application.address.create.CreateAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.delete.DeleteAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.get.GetAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.retrieve.list.ListAddressUsecase;
import com.arthurlamberti.ecommerce.application.address.update.UpdateAddressUsecase;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressListResponse;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressResponse;
import com.arthurlamberti.ecommerce.infrastructure.address.models.CreateAddressRequest;
import com.arthurlamberti.ecommerce.infrastructure.address.presenters.AddressApiPresenter;
import com.arthurlamberti.ecommerce.infrastructure.api.AddressAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class AddressController implements AddressAPI {

    private final CreateAddressUsecase createAddressUsecase;
    private final ListAddressUsecase listAddressUsecase;
    private final GetAddressUsecase getAddressUsecase;
    private final DeleteAddressUsecase deleteAddressUsecase;
    private final UpdateAddressUsecase updateAddressUsecase;

    public AddressController(
            final CreateAddressUsecase createAddressUsecase,
            final ListAddressUsecase listAddressUsecase,
            final GetAddressUsecase getAddressUsecase,
            final DeleteAddressUsecase deleteAddressUsecase,
            final UpdateAddressUsecase updateAddressUsecase) {
        this.createAddressUsecase = createAddressUsecase;
        this.listAddressUsecase = listAddressUsecase;
        this.getAddressUsecase = getAddressUsecase;
        this.deleteAddressUsecase = deleteAddressUsecase;
        this.updateAddressUsecase = updateAddressUsecase;
    }

    @Override
    public ResponseEntity<?> createAddress(CreateAddressRequest input) {
        final var aCommand = CreateAddressCommand.with(
                input.country(),
                input.state(),
                input.city(),
                input.street(),
                input.zipCode(),
                input.number(),
                input.complement(),
                input.customerId(),
                input.sellerId()
        );

        final var output = this.createAddressUsecase.execute(aCommand);

        return ResponseEntity.created(URI.create("/addresses/" + output.id())).body(output);
    }

    @Override
    public Pagination<AddressListResponse> list(String search, int page, int perPage, String sort, String direction) {
        return this.listAddressUsecase.execute(new SearchQuery(page,perPage,search, sort, direction)).map(AddressApiPresenter::present);
    }

    @Override
    public AddressResponse getById(String id) {
        return AddressApiPresenter.present(this.getAddressUsecase.execute(id));
    }

    @Override
    public void deleteById(String id) {
        this.deleteAddressUsecase.execute(id);
    }
}
