package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
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

    @Test
    public void givenAnInvalidNullCountryCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be null";

        final var aCommand = CreateAddressCommand.with(
                null,
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyCountryCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be empty";

        final var aCommand = CreateAddressCommand.with(
                " ",
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidNullStateCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                null,
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyStateCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be empty";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                " ",
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidNullCityCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                null,
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyCityCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be empty";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                " ",
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidNullStreetCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                null,
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyStreetCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                null,
                expectedAddress.getZipCode(),
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }
    @Test
    public void givenAnInvalidNullZipcodeCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                null,
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyZipcodeCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be empty";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                " ",
                expectedAddress.getNumber(),
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidNullNumberCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'number' should not be null";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                null,
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }

    @Test
    public void givenAnInvalidEmptyNumberCommand_whenCallsCreateAddres_shouldReturnAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'number' should not be empty";

        final var aCommand = CreateAddressCommand.with(
                expectedAddress.getCountry(),
                expectedAddress.getState(),
                expectedAddress.getCity(),
                expectedAddress.getStreet(),
                expectedAddress.getZipCode(),
                "",
                expectedAddress.getComplement()
        );

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verifyNoInteractions(addressGateway);
    }
}