package com.arthurlamberti.ecommerce.application.address.update;

import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateAddressUsecaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateAddressUsecase usecase;

    @Mock
    private AddressGateway addressGateway;

    @Override
    protected List<Object> getMocks() {
        return null;
    }

    @Test
    void givenValidUpdateAddressCommand_whenFieldsAreValid_thenShouldPassValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();
        final var updatedAddress = Fixture.AddressFixture.validAddress();

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                updatedAddress.getCountry(),
                updatedAddress.getState(),
                updatedAddress.getCity(),
                updatedAddress.getStreet(),
                updatedAddress.getZipCode(),
                updatedAddress.getNumeral(),
                updatedAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(Address.with(anAddress)));
        when(addressGateway.update(any())).thenAnswer(returnsFirstArg());

        assertNotEquals(anAddress.getCountry(), updatedAddress.getCountry());
        assertNotEquals(anAddress.getState(), updatedAddress.getState());
        assertNotEquals(anAddress.getCity(), updatedAddress.getCity());
        assertNotEquals(anAddress.getStreet(), updatedAddress.getStreet());
        assertNotEquals(anAddress.getZipCode(), updatedAddress.getZipCode());
        assertNotEquals(anAddress.getNumeral(), updatedAddress.getNumeral());

        final var actualOutput = usecase.execute(aCommand);
        assertEquals(anAddress.getId().getValue(), actualOutput.id());

        verify(addressGateway, times(1)).findById(any());
        verify(addressGateway, times(1)).update(argThat(actualAddress ->
                Objects.equals(updatedAddress.getCountry(), actualAddress.getCountry())
                        && Objects.equals(updatedAddress.getState(), actualAddress.getState())
                        && Objects.equals(updatedAddress.getCity(), actualAddress.getCity())
                        && Objects.equals(updatedAddress.getStreet(), actualAddress.getStreet())
                        && Objects.equals(updatedAddress.getZipCode(), actualAddress.getZipCode())
                        && Objects.equals(updatedAddress.getNumeral(), actualAddress.getNumeral())
        ));
    }

    @Test
    void givenUpdateAddressCommand_whenCountryIsNull_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                null,
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenCountryIsEmpty_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'country' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                " ",
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenStateIsNull_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                null,
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenStateIsEmpty_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'state' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                " ",
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenCityIsNull_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                null,
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenCityIsEmpty_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'city' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                " ",
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenStreetIsNull_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                null,
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenStreetIsEmpty_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'street' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                " ",
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenZipcodeIsNull_thenShouldFailValidation() {
        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                null,
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenZipcodeIsEmpty_thenShouldFailValidation() {

        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'zipcode' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                " ",
                anAddress.getNumeral(),
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenNumberIsNull_thenShouldFailValidation() {

        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numeral' should not be null";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                null,
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }

    @Test
    void givenUpdateAddressCommand_whenNumberIsEmpty_thenShouldFailValidation() {

        final var anAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numeral' should not be empty";

        final var aCommand = UpdateAddressCommand.with(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                " ",
                anAddress.getComplement()
        );

        when(addressGateway.findById(any())).thenReturn(Optional.of(anAddress));

        final var actualOutput = assertThrows(NotificationException.class, () -> usecase.execute(aCommand));
        assertNotNull(actualOutput);
        assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        assertEquals(expectedErrorMessage, actualOutput.getFirstError().get().message());
        verify(addressGateway, times(0)).update(any());
    }
}