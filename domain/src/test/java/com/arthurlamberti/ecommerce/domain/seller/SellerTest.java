package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SellerTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewSeller_shouldInstantiateASeller() {
        final var expectedName = Fixture.sellerName();
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedIsActive = Boolean.TRUE;

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive);
        assertNotNull(actualSeller);
        assertNotNull(actualSeller.getId());
        assertEquals(expectedName, actualSeller.getName());
        assertEquals(expectedIsActive, actualSeller.isActive());
        assertNotNull(actualSeller.getCreatedAt());
        assertNotNull(actualSeller.getUpdatedAt());
        assertNull(actualSeller.getDeletedAt());
    }

    @Test
    public void givenNullDescription_whenCallNewSeller_shouldInstantiateASeller() {
        final var expectedName = Fixture.sellerName();
        final String expectedDescription = null;
        final var expectedIsActive = Boolean.TRUE;

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive);
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

        final var actualSeller = Seller.newSeller(expectedName, expectedDescription, expectedIsActive);
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
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive())
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewSeller_shouldReceiveAnError(){
        final var name = "";
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive())
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthLessThan3_whenCallNewSeller_shouldReceiveAnError(){
        final var name = "ab";
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive())
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthGreatherThan255_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.characters(400);
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.sellerDescription();
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive())
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthGreatherThan3000_whenCallNewSeller_shouldReceiveAnError(){
        final var name = Fixture.sellerName();
        final var expectedErrorCount = 1;
        final var expectedDescription = Fixture.characters(4000);
        final var expectedErrorMessage = "'description' must contains a maximum of 3000 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Seller.newSeller(name, expectedDescription, Fixture.isActive())
        );
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }
}