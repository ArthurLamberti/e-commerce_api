package com.arthurlamberti.ecommerce.application.address.retrieve.get;

import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetAddressUsecaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultGetAddressUsecase usecase;

    @Mock
    private AddressGateway addressGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(addressGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetGenre_shouldReturnGenre() {
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedId = expectedAddress.getId();

        when(addressGateway.findById(any())).thenReturn(Optional.of(expectedAddress));

        final var actualAddress = usecase.execute(expectedId.getValue());
        assertNotNull(actualAddress);
        assertEquals(expectedAddress.getId().getValue(),actualAddress.id());
        assertEquals(expectedAddress.getCountry(),actualAddress.country());
        assertEquals(expectedAddress.getState(),actualAddress.state());
        assertEquals(expectedAddress.getCity(),actualAddress.city());
        assertEquals(expectedAddress.getStreet(),actualAddress.street());
        assertEquals(expectedAddress.getZipCode(),actualAddress.zipcode());
        assertEquals(expectedAddress.getNumeral(),actualAddress.number());
        assertEquals(expectedAddress.isActive(),actualAddress.isActive());
        assertEquals(expectedAddress.getCreatedAt(),actualAddress.createdAt());
    }

    @Test
    public void givenAValidId_whenCallsGetGenreAndDoesNotExists_shouldReturnNotFound() {
        final var expectedErrorMessage = "Address with ID 123 was not found";

        final var expectedId = AddressID.from("123");

        when(addressGateway.findById(any()))
                .thenReturn(Optional.empty());

        // when
        final var actualException = Assertions.assertThrows(NotFoundException.class, () -> {
            usecase.execute(expectedId.getValue());
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}