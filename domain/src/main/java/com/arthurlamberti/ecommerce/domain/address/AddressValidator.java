package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class AddressValidator extends Validator {
    private final Address anAddress;

    private final int COMPLEMENT_MAX_LENGTH = 100;

    protected AddressValidator(final Address anAddress, final ValidationHandler aHandler) {
        super(aHandler);
        this.anAddress = anAddress;
    }

    @Override
    public void validate() {
        checkCountryConstraints();
        checkStateConstraints();
        checkCityConstraints();
        checkStreetConstraints();
        checkZipCodeConstraints();
        checkNumberConstraints();
        checkComplementConstraints();
        checkOwnerConstraints();
    }

    private void checkOwnerConstraints() {
        final var customerId = anAddress.getCustomerId();
        final var selledId = anAddress.getSellerId();

        if ((isNull(customerId) || customerId.isBlank()) && (isNull(selledId) || selledId.isBlank())) {
            this.validationHandler().append(new Error("'customerId' or 'sellerId' should not be null"));
            return;
        }

        if ((!isNull(customerId) && !customerId.isBlank()) && (!isNull(selledId) && !selledId.isBlank())) {
            this.validationHandler().append(new Error("'customerId' and 'sellerId' must be unique"));
        }
    }

    private void checkCountryConstraints() {
        final var country = anAddress.getCountry();
        if (isNull(country)) {
            this.validationHandler().append(new Error("'country' should not be null"));
            return;
        }

        if (country.isBlank()) {
            this.validationHandler().append(new Error("'country' should not be empty"));
        }
    }

    private void checkStateConstraints() {
        final var state = anAddress.getState();
        if (isNull(state)) {
            this.validationHandler().append(new Error("'state' should not be null"));
            return;
        }

        if (state.isBlank()) {
            this.validationHandler().append(new Error("'state' should not be empty"));
        }
    }

    private void checkCityConstraints() {
        final var city = anAddress.getCity();
        if (isNull(city)) {
            this.validationHandler().append(new Error("'city' should not be null"));
            return;
        }

        if (city.isBlank()) {
            this.validationHandler().append(new Error("'city' should not be empty"));
        }
    }

    private void checkStreetConstraints() {
        final var street = anAddress.getStreet();
        if (isNull(street)) {
            this.validationHandler().append(new Error("'street' should not be null"));
            return;
        }

        if (street.isBlank()) {
            this.validationHandler().append(new Error("'street' should not be empty"));
        }
    }

    private void checkZipCodeConstraints() {
        final var zipcode = anAddress.getZipCode();
        if (isNull(zipcode)) {
            this.validationHandler().append(new Error("'zipcode' should not be null"));
            return;
        }

        if (zipcode.isBlank()) {
            this.validationHandler().append(new Error("'zipcode' should not be empty"));
        }
    }

    private void checkNumberConstraints() {
        final var number = anAddress.getNumeral();
        if (isNull(number)) {
            this.validationHandler().append(new Error("'numeral' should not be null"));
            return;
        }

        if (number.isBlank()) {
            this.validationHandler().append(new Error("'numeral' should not be empty"));
        }
    }

    private void checkComplementConstraints() {
        final var complement = anAddress.getComplement();
        if (isNull(complement) || complement.isBlank())
            return;

        if (complement.trim().length() > COMPLEMENT_MAX_LENGTH)
            this.validationHandler().append(new Error("'complement' should contains max of 100 characters"));
    }
}
