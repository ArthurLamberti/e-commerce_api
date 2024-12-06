package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.IntegrationTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

@IntegrationTest
public class CreateAddressUseCaseIT {

    @Autowired
    private CreateAddressUsecase createAddressUsecase;

    @Autowired
    private AddressRepository addressRepository;

    @SpyBean
    private AddressGateway addressGateway;

    @Test
    public void givenAValidCommand_whenCallsCreateAddress_shouldReturnIt() {
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var output = createAddressUsecase.execute(aCommand);
        assertNotNull(output);
        assertNotNull(output.id());

        final var actualAddress = this.addressRepository.findById(output.id()).get();
        assertEquals(expectedAddress.getCountry(), actualAddress.getCountry());
    }

    //TODO implement tests
    @Test
    public void givenAnInvalidNullCountryCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyCountryCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidNullStateCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyStateCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidNullCityCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyCityCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidNullStreetCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyStreetCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidNullZipcodeCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyZipcodeCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidNullNumberCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }

    @Test
    public void givenAnInvalidEmptyNumberCommand_whenCallsCreateAddres_shouldReturnAnError() {
    }
}
