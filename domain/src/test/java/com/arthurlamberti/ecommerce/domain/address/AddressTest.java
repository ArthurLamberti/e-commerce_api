package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

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

    @Test
    public void givenInvalidNullCountry_whenCallNewAddress_shouldReceiveAnException(){
        final String expectedCountry = null;
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyCountry_whenCallNewAddress_shouldReceiveAnException(){
        final String expectedCountry = " ";
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullState_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final String expectedState = null;
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyState_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final String expectedState = " ";
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullCity_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final String expectedCity = null;
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyCity_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final String expectedCity = "";
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullStreet_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final String expectedStreet = null;
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyStreet_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = "";
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullZipCode_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final String expectedZipCode = null;
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyZipCode_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = "";
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullNumber_whenCallNewAddress_shouldReceiveAnException(){

        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final String expectedNumber = null;
        final var expectedComplement = Fixture.characters(1,99);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'number' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());}

    @Test
    public void givenInvalidEmptyNumber_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = "";
        final var expectedComplement = Fixture.characters(1,99);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'number' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
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
    public void givenInvalidComplementGreaterThan100_whenCallNewAddress_shouldReceiveAnException(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(101,300);
        final var expectedCustomerId = Fixture.uuid();
        final String expectedSellerId = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'complement' should contains max of 100 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenACustomerAndSellerId_whenCallsNewAddress_shouldReceiveAnError() {
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(0,89);
        final var expectedCustomerId = Fixture.uuid();
        final var expectedSellerId = Fixture.uuid();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customerId' and 'sellerId' must be unique";

        final var actualError = assertThrows(
                NotificationException.class,
                () ->  Address.newAddress(
                        expectedCountry,
                        expectedState,
                        expectedCity,
                        expectedStreet,
                        expectedZipCode,
                        expectedNumber,
                        expectedComplement,
                        expectedCustomerId,
                        expectedSellerId)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());

    }

    @Test
    public void givenANullCustomerAndSellerId_whenCallsNewAddress_shouldReceiveAnError(){

    }

    @Test
    public void givenAValidAddress_whenCallDeactivate_shouldReceiveOk(){
        final var expectedCountry = Fixture.AddressFixture.country();
        final var expectedState = Fixture.AddressFixture.state();
        final var expectedCity = Fixture.AddressFixture.city();
        final var expectedStreet = Fixture.AddressFixture.street();
        final var expectedZipCode = Fixture.AddressFixture.zipCode();
        final var expectedNumber = Fixture.AddressFixture.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.FALSE;
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

        final var actualUpdatedAt = actualAddress.getUpdatedAt();

        actualAddress.deactivate();

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
        assertNotNull(actualAddress.getDeletedAt());
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