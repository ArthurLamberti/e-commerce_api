package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SellerTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewSeller_shouldInstantiateASeller() {
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedIsActive = Boolean.TRUE;
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive, expedtedDocument, expectedAddress);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expectedIsActive, actualSeller.isActive());
        assertEquals(expedtedDocument, actualSeller.getDocument());
        assertEquals(expectedAddress, actualSeller.getAddress());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenNullDescription_whenCallNewSeller_shouldInstantiateASeller() {
        final var expectedName = Fixture.sellerName();
        final String expectedDescription = null;
        final var expectedIsActive = Boolean.TRUE;
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive, expedtedDocument, expectedAddress);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expectedIsActive, actualSeller.isActive());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenEmptyDescription_whenCallNewSeller_shouldInstantiateASeller() {
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = "";
        final var expectedIsActive = Boolean.TRUE;
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive, expedtedDocument, expectedAddress);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expectedIsActive, actualSeller.isActive());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenInvalidNullName_whenCallNewSeller_shouldReceiveAnError(){
        final String name = null;
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewSeller_shouldReceiveAnError(){
        final var name = "";
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthLessThan3_whenCallNewSeller_shouldReceiveAnError(){
        final var name = "ab";
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthGreatherThan255_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.characters(400);
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthGreatherThan3000_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.characters(4000);
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'description' must contains a maximum of 3000 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullDocument_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.SellerFixture.description();
        final String expedtedDocument = null;
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'document' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyDocument_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.SellerFixture.description();
        final var expedtedDocument = "";
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'document' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullAddress_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.SellerFixture.description();
        final var expedtedDocument = Fixture.document();
        final Address expectedAddress = null;
        final var expectedErrorMessage = "'address' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAValidSeller_WhenCallDeactivate_shouldReturnOK(){
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, true, expedtedDocument, expectedAddress);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertTrue(actualSeller.isActive());

        actualSeller.deactivate();

        assertFalse(actualSeller.isActive());
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expedtedDocument, actualSeller.getDocument());
        assertEquals(expectedAddress, actualSeller.getAddress());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNotNull(actualSeller.getDeletedAt());
        assertTrue(actualSeller.getUpdatedAt().isAfter(actualSeller.getCreatedAt()));
    }

    @Test
    public void givenAnInactiveSeller_WhenCallActivate_shouldReturnOK(){
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = Fixture.sellerDescription();
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, true, expedtedDocument, expectedAddress);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertTrue(actualSeller.isActive());

        actualSeller.deactivate();
        final var actualUpdatedAt = actualSeller.getUpdatedAt();
        assertFalse(actualSeller.isActive());
        assertTrue(actualUpdatedAt.isAfter(actualSeller.getCreatedAt()));
        actualSeller.activate();
        assertTrue(actualSeller.isActive());
        assertTrue(actualSeller.getUpdatedAt().isAfter(actualUpdatedAt));
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expedtedDocument, actualSeller.getDocument());
        assertEquals(expectedAddress, actualSeller.getAddress());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenAValidAddress_WhenCallChangeAddress_shouldReturnOK(){
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedIsActive = Boolean.TRUE;
        final var expedtedDocument = Fixture.document();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive, expedtedDocument, Fixture.AddressFixture.validAddress());
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());

        final var actualAddress = actualSeller.getAddress();
        actualSeller.changeAddress(expectedAddress);
        assertEquals(expectedAddress, actualSeller.getAddress());
        assertNotEquals(actualAddress, actualSeller.getAddress());

        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expectedIsActive, actualSeller.isActive());
        assertEquals(expedtedDocument, actualSeller.getDocument());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenANullAddress_WhenCallChangeAddress_shouldReturnAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.SellerFixture.description();
        final var expedtedDocument = Fixture.document();
        final Address expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedErrorMessage = "'address' should not be null";

        final var actualSeller = Seller.newSeller(name, expectedDescription, Fixture.isActive(), expedtedDocument, expectedAddress);

        final var actualException = assertThrows(
                NotificationException.class,
                () -> actualSeller.changeAddress(null)
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

    }
}