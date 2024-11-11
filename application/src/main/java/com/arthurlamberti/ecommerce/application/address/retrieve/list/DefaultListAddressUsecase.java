package com.arthurlamberti.ecommerce.application.address.retrieve.list;

import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;

public class DefaultListAddressUsecase extends ListAddressUsecase{

    private final AddressGateway addressGateway;

    public DefaultListAddressUsecase(final AddressGateway addressGateway){
        this.addressGateway = addressGateway;
    }

    @Override
    public Pagination<ListAddressOutput> execute(SearchQuery aQuery) {
        return this.addressGateway.findAll(aQuery).map(ListAddressOutput::from);
    }
}
