package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewAddress_shouldInstantiateAnAddress() {
        final var expectedCountry = Fixture.Address.country();
        final var expectedState = Fixture.Address.state();
        final var expectedCity = Fixture.Address.city();
        final var expectedStreet = Fixture.Address.street();
        final var expectedZipCode = Fixture.Address.zipCode();
        final var expectedNumber = Fixture.Address.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.TRUE;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement);

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumber());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertEquals(actualAddress.getCreatedAt(), actualAddress.getUpdatedAt());
        assertNull(actualAddress.getDeletedAt());
    }
    public void givenInvalidNullCountry_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyCountry_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullState_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyState_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullCity_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyCity_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullStreet_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyStreet_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullNumber_whenCallNewAddress_shouldReceiveAnException(){}

    @Test
    public void givenInvalidEmptyNumber_whenCallNewAddress_shouldReceiveAnException(){

        final var expectedCountry = Fixture.Address.country();
        final var expectedState = Fixture.Address.state();
        final var expectedCity = Fixture.Address.city();
        final var expectedStreet = Fixture.Address.street();
        final var expectedZipCode = Fixture.Address.zipCode();
        final var expectedNumber = Fixture.Address.number();
        final var expectedComplement = "";
        final var expectedIsActive = Boolean.TRUE;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement);

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumber());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertEquals(actualAddress.getCreatedAt(), actualAddress.getUpdatedAt());
        assertNull(actualAddress.getDeletedAt());
    }

    @Test
    public void givenValidNullComplement_whenCallNewAddress_shouldInstantiateAnAddress(){

        final var expectedCountry = Fixture.Address.country();
        final var expectedState = Fixture.Address.state();
        final var expectedCity = Fixture.Address.city();
        final var expectedStreet = Fixture.Address.street();
        final var expectedZipCode = Fixture.Address.zipCode();
        final var expectedNumber = Fixture.Address.number();
        final String expectedComplement = null;
        final var expectedIsActive = Boolean.TRUE;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement);

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumber());
        assertNull(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertEquals(actualAddress.getCreatedAt(), actualAddress.getUpdatedAt());
        assertNull(actualAddress.getDeletedAt());
    }
    public void givenAValidAddress_whenCallDeactivate_shouldReceiveOk(){

        final var expectedCountry = Fixture.Address.country();
        final var expectedState = Fixture.Address.state();
        final var expectedCity = Fixture.Address.city();
        final var expectedStreet = Fixture.Address.street();
        final var expectedZipCode = Fixture.Address.zipCode();
        final var expectedNumber = Fixture.Address.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.FALSE;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement);

        final var actualUpdatedAt = actualAddress.getUpdatedAt();

        actualAddress.deactivate();

        assertNotNull(actualAddress);
        assertNotNull(actualAddress.getId());
        assertEquals(expectedCountry, actualAddress.getCountry());
        assertEquals(expectedState, actualAddress.getState());
        assertEquals(expectedCity, actualAddress.getCity());
        assertEquals(expectedStreet, actualAddress.getStreet());
        assertEquals(expectedZipCode, actualAddress.getZipCode());
        assertEquals(expectedNumber, actualAddress.getNumber());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertTrue(actualAddress.getCreatedAt().isBefore(actualAddress.getUpdatedAt()));
        assertNotNull(actualAddress.getDeletedAt());
    }
    public void givenAValidInactiveAddress_whenCallActivate_shouldReceiveOk(){
        final var expectedCountry = Fixture.Address.country();
        final var expectedState = Fixture.Address.state();
        final var expectedCity = Fixture.Address.city();
        final var expectedStreet = Fixture.Address.street();
        final var expectedZipCode = Fixture.Address.zipCode();
        final var expectedNumber = Fixture.Address.number();
        final var expectedComplement = Fixture.characters(1, 100);
        final var expectedIsActive = Boolean.TRUE;

        final var actualAddress = Address.newAddress(
                expectedCountry,
                expectedState,
                expectedCity,
                expectedStreet,
                expectedZipCode,
                expectedNumber,
                expectedComplement);
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
        assertEquals(expectedNumber, actualAddress.getNumber());
        assertEquals(expectedComplement, actualAddress.getComplement());
        assertEquals(expectedIsActive, actualAddress.isActive());
        assertTrue(actualAddress.getCreatedAt().isBefore(actualAddress.getUpdatedAt()));
        assertNull(actualAddress.getDeletedAt());
    }

}