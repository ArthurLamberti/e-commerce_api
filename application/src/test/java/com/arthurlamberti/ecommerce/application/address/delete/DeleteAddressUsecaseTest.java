package com.arthurlamberti.ecommerce.application.address.delete;

import com.arthurlamberti.ecommerce.application.UnitUseCase;
import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteAddressUsecaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteAddressUsecase usecase;

    @Mock
    private AddressGateway addressGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(addressGateway);
    }

    @Test
    void givenValidId_whenExecute_thenShouldCallDeleteById() {
        final var existentAddress = Fixture.AddressFixture.validAddress();
        final var expectedId = existentAddress.getId();

        doNothing().when(addressGateway).deleteById(any());
        // Act
        usecase.execute(expectedId.getValue());

        // Assert
        verify(addressGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    void givenInvalidId_whenExecute_thenShouldCallDeleteById() {
        final var expectedId = AddressID.unique();

        doNothing().when(addressGateway).deleteById(any());
        usecase.execute(expectedId.getValue());

        verify(addressGateway, times(1)).deleteById(eq(expectedId));
    }

    @Test
    void givenValidId_whenGatewayThrowsException_thenShouldPropagate() {
        final var validId = AddressID.unique();
        doThrow(new RuntimeException("Gateway error"))
                .when(addressGateway).deleteById(any());

        assertThrows(RuntimeException.class, () -> usecase.execute(validId.getValue()));

        verify(addressGateway, times(1)).deleteById(eq(validId));
    }
}