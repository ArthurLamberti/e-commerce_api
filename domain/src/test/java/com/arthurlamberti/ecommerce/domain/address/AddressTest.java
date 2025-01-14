package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewAddress_shouldInstantiateAnAddress() {
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.TRUE;
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement,
                expectedCustomerId,
                expectedSellerId);

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumeral());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertEquals(expectedCustomerId, actualAddress.getCustomerId());
        assertEquals(expectedSellerId, actualAddress.getSellerId());
        assertEquals(actualAddress.getCreatedAt(), actualAddress.getUpdatedAt());
        assertNull(actualAddress.getDeletedAt());
    }

    @ParameterizedTest
    @CsvSource({
            ",exState,exCity,exStreet,exZipCode,exNumeral,exCustomerId,,1,'country' should not be null",
            "'',exState,exCity,exStreet,exZipCode,exNumeral,exCustomerId,,1,'country' should not be empty",
            "exCountry,,exCity,exStreet,exZipCode,exNumeral,exCustomerId,,1,'state' should not be null",
            "exCountry,'',exCity,exStreet,exZipCode,exNumeral,exCustomerId,,1,'state' should not be empty",
            "exCountry,exState,,exStreet,exZipCode,exNumeral,exCustomerId,,1,'city' should not be null",
            "exCountry,exState,'',exStreet,exZipCode,exNumeral,exCustomerId,,1,'city' should not be empty",
            "exCountry,exState,exCity,,exZipCode,exNumeral,exCustomerId,,1,'street' should not be null",
            "exCountry,exState,exCity,'',exZipCode,exNumeral,exCustomerId,,1,'street' should not be empty",
            "exCountry,exState,exCity,exStreet,,exNumeral,exCustomerId,,1,'zipcode' should not be null",
            "exCountry,exState,exCity,exStreet,'',exNumeral,exCustomerId,,1,'zipcode' should not be empty",
            "exCountry,exState,exCity,exStreet,exZipCode,,exCustomerId,,1,'numeral' should not be null",
            "exCountry,exState,exCity,exStreet,exZipCode,'',exCustomerId,,1,'numeral' should not be empty",
            "exCountry,exState,exCity,exStreet,exZipCode,exNumeral,exCustomerId,exSellerId,1,'customerId' and 'sellerId' must be unique",
            "exCountry,exState,exCity,exStreet,exZipCode,exNumeral,,,1,'customerId' or 'sellerId' should not be null",
    })
    public void givenInvalidParams_whenCallNewAddress_shouldThrowException(
        final String expectedCountry,
        final String expectedState,
        final String expectedCity,
        final String expectedStreet,
        final String expectedZipCode,
        final String expectedNumeral,
        final String expectedCustomerId,
        final String expectedSellerId,
        final Integer qtyErrors,
        final String errorMessage
    ) {
        final var actualException = assertThrows(
                NotificationException.class,
                () -> Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumeral,
                        "",
                        expectedCustomerId,
                        expectedSellerId
                )
        );

        assertNotNull(actualException);
        assertEquals(qtyErrors, actualException.getErrors().size());
        assertEquals(errorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenValidNullComplement_whenCallNewAddress_shouldInstantiateAnAddress(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final String expectedComplement = null;
        final var expectedIsActive = Boolean.TRUE;
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement,
                expectedCustomerId,
                expectedSellerId);

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumeral());
        assertNull(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertEquals(actualAddress.getCreatedAt(), actualAddress.getUpdatedAt());
        assertNull(actualAddress.getDeletedAt());
    }

    @Test
    public void givenAValidAddress_whenCallDeactivate_shouldReceiveOk() throws InterruptedException {
        final var actualAddress = Fixture.AddressFixture.validAddress();
        final var updatedAddress = actualAddress.clone();

        Thread.sleep(1000);
        updatedAddress.deactivate();

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(actualAddress.getCountry(),updatedAddress.getCountry());
        assertEquals(actualAddress.getState(),updatedAddress.getState());
        assertEquals(actualAddress.getCity(),updatedAddress.getCity());
        assertEquals(actualAddress.getStreet(),updatedAddress.getStreet());
        assertEquals(actualAddress.getZipCode(),updatedAddress.getZipCode());
        assertEquals(actualAddress.getNumeral(),updatedAddress.getNumeral());
        assertEquals(actualAddress.getComplement(),updatedAddress.getComplement());
        assertNotEquals(actualAddress.isActive(),updatedAddress.isActive());
        assertTrue(actualAddress.getUpdatedAt().isBefore(updatedAddress.getUpdatedAt()));
        assertNotNull(updatedAddress.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveAddress_whenCallActivate_shouldReceiveOk(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.TRUE;
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement,
                expectedCustomerId,
                expectedSellerId);
        actualAddress.deactivate();

        assertFalse(actualAddress.isActive());
        assertNotNull(actualAddress.getDeletedAt());

        actualAddress.activate();

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumeral());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertTrue(actualAddress.getCreatedAt().isBefore(actualAddress.getUpdatedAt()));
        assertNull(actualAddress.getDeletedAt());
    }

    @Test
    public void givenAValidAddress_whenCallUpdateAddress_shouldReceiveOK(){
        final var initialAddress = Fixture.AddressFixture.validAddress();

        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 99);
        final var expectedIsActive = Boolean.TRUE;
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var initialAddressCopy = initialAddress.clone();

        final var updatedAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement,
                expectedCustomerId,
                expectedSellerId);

        assertNotNull(initialAddress);
        assertNotNull(initialAddress.getId());
        assertEquals(initialAddressCopy.getCountry(), initialAddress.getCountry());
        assertEquals(initialAddressCopy.getState(), initialAddress.getState());
        assertEquals(initialAddressCopy.getCity(), initialAddress.getCity());
        assertEquals(initialAddressCopy.getStreet(), initialAddress.getStreet());
        assertEquals(initialAddressCopy.getZipCode(), initialAddress.getZipCode());
        assertEquals(initialAddressCopy.getNumeral(), initialAddress.getNumeral());
        assertEquals(initialAddressCopy.getComplement(), initialAddress.getComplement());
        assertEquals(initialAddressCopy.isActive(), initialAddress.isActive());
        assertEquals(initialAddress.getCreatedAt(),initialAddress.getUpdatedAt());

        initialAddress.updateAddress(
                updatedAddress.getCountry(),
                updatedAddress.getState(),
                updatedAddress.getCity(),
                updatedAddress.getStreet(),
                updatedAddress.getZipCode(),
                updatedAddress.getNumeral(),
                updatedAddress.getComplement()
        );

        assertNotEquals(initialAddressCopy.getCountry(), initialAddress.getCountry());
        assertNotEquals(initialAddressCopy.getState(), initialAddress.getState());
        assertNotEquals(initialAddressCopy.getCountry(), initialAddress.getCity());
        assertNotEquals(initialAddressCopy.getStreet(), initialAddress.getStreet());
        assertNotEquals(initialAddressCopy.getZipCode(), initialAddress.getZipCode());
        assertNotEquals(initialAddressCopy.getNumeral(), initialAddress.getNumeral());
        assertNotEquals(initialAddressCopy.getComplement(), initialAddress.getComplement());
        assertEquals(initialAddressCopy.isActive(), initialAddress.isActive());

        assertEquals(expectedCountry, initialAddress.getCountry());
        assertEquals(expectedState, initialAddress.getState());
        assertEquals(expectedCity, initialAddress.getCity());
        assertEquals(expectedStreet, initialAddress.getStreet());
        assertEquals(expectedZipCode, initialAddress.getZipCode());
        assertEquals(expectedNumber, initialAddress.getNumeral());
        assertEquals(expectedComplement, initialAddress.getComplement());
        assertEquals(expectedIsActive, initialAddress.isActive());
        assertTrue(initialAddress.getCreatedAt().isBefore(initialAddress.getUpdatedAt()));
    }
}