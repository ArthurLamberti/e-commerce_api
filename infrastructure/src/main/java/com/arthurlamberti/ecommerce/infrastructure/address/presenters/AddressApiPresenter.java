package com.arthurlamberti.ecommerce.infrastructure.address.presenters;

import com.arthurlamberti.ecommerce.application.address.retrieve.get.AddressOutput;
import com.arthurlamberti.ecommerce.application.address.retrieve.list.ListAddressOutput;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressListResponse;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressResponse;

public interface AddressApiPresenter {
    static AddressListResponse present(ListAddressOutput output) {
        return new AddressListResponse(
                output.id(),
                output.country(),
                output.state(),
                output.city(),
                output.street(),
                output.zipcode(),
                output.number(),
                output.complement(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static AddressResponse present(AddressOutput output) {
        return new AddressResponse(
                output.id(),
                output.country(),
                output.state(),
                output.city(),
                output.street(),
                output.zipcode(),
                output.number(),
                output.complement(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }
}
