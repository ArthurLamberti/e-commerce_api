package com.arthurlamberti.ecommerce.domain.customer;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewCustomer_shouldInstantiateACustomer() {
        final var expectedName = Fixture.name();
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualCustomer = Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress);

        assertNotNull(actualCustomer);
        assertNotNull(actualCustomer.getId());
        assertEquals(expectedName, actualCustomer.getName());
        assertEquals(expectedEmail, actualCustomer.getEmail());
        assertEquals(expectedDocument, actualCustomer.getDocument());
        assertNotNull(actualCustomer.getAddress());
        assertTrue(actualCustomer.isActive());
        assertNotNull(actualCustomer.getCreatedAt());
        assertNotNull(actualCustomer.getUpdatedAt());
        assertNull(actualCustomer.getDeletedAt());
    }

    @Test
    public void givenInvalidNullNameParam_whenCallNewCustomer_shouldReceiveAnException() {
        final String expectedName = null;
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyNameParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = "";
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameLengthGreaterThan255_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.characters(256, 300);
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameLengthLessThan3_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = "ab";
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";


        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullEmailParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.name();
        final String expectedEmail = null;
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyEmailParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.name();
        final var expectedEmail = "";
        final var expectedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullDocumentParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.name();
        final var expectedEmail = Fixture.email();
        final String expectedDocument = null;
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyDocumentParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.name();
        final var expectedEmail = Fixture.email();
        final var expectedDocument = "";
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullAddressParam_whenCallNewCustomer_shouldReceiveAnException() {
        final var expectedName = Fixture.name();
        final var expectedEmail = Fixture.email();
        final var expectedDocument = Fixture.document();
        final Address expectedAddress = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'address' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedEmail, expectedDocument, expectedAddress)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

    }
}