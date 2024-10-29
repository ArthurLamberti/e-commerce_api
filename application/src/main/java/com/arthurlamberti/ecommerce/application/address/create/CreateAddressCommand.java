package com.arthurlamberti.ecommerce.application.address.create;

public record CreateAddressCommand (
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement
) {
    public static CreateAddressCommand with(
            final String aCountry,
            final String aState,
            final String aCity,
            final String aStreet,
            final String aZipcode,
            final String aNumber,
            final String aComplement
    ) {
        return new CreateAddressCommand(aCountry, aState, aCity, aStreet, aZipcode, aNumber, aComplement);
    }
}