package com.arthurlamberti.ecommerce.infrastructure.customer.models;

import com.arthurlamberti.ecommerce.application.customer.retrieve.AddressSummary;
import com.arthurlamberti.ecommerce.domain.address.Address;

public record AddressSummaryResponse(
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement,
        boolean isActive
) {
    public static AddressSummaryResponse from(final AddressSummary address) {
        return new AddressSummaryResponse(
                address.country(),
                address.state(),
                address.city(),
                address.street(),
                address.zipcode(),
                address.number(),
                address.complement(),
                address.isActive()
        );
    }
}
