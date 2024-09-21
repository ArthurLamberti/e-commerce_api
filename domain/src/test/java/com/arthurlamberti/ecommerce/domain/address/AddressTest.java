package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.UnitTest;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest extends UnitTest {

    public void givenValidParams_whenCallNewAddress_shouldInstantiateAnAddress() {}
    public void givenInvalidNullCountry_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyCountry_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullProvince_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyProvince_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullCity_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyCity_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullStreet_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyStreet_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidNullNumber_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenInvalidEmptyNumber_whenCallNewAddress_shouldReceiveAnException(){}
    public void givenValidNullComplement_whenCallNewAddress_shouldInstantiateAnAddress(){}
    public void givenAValidAddress_whenCallDeactivate_shouldReceiveOk(){}
    public void givenAValidInactiveAddress_whenCallActivate_shouldReceiveOk(){}

}