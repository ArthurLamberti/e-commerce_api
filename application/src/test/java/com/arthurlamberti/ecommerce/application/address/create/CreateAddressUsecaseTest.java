package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateAddressUsecaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateAddressUsecase usecase;

    @Mock
    private AddressGateway addressGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(addressGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateAddres_shouldReturnIt(){
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

        when(addressGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualResult = usecase.execute(aCommand);

        assertNotNull(actualResult);

        verify(addressGateway, times(1)).create(
                argThat(address ->
                        Objects.nonNull(address.getId())
                        && Objects.equals(expectedAddress.getCountry(), address.getCountry())
                        && Objects.equals(expectedAddress.getState(), address.getState())
                        && Objects.equals(expectedAddress.getCity(), address.getCity())
                        && Objects.equals(expectedAddress.getStreet(), address.getStreet())
                        && Objects.equals(expectedAddress.getZipCode(), address.getZipCode())
                        && Objects.equals(expectedAddress.getNumber(), address.getNumber())
                        && Objects.equals(expectedAddress.getComplement(), address.getComplement())
                        && Objects.equals(expectedAddress.isActive(), address.isActive())
                        && Objects.nonNull(address.getCreatedAt())
                        && Objects.nonNull(address.getUpdatedAt())
                        && Objects.isNull(address.getDeletedAt())
                )
        );
    }

    //TODO: complete with more tests
}