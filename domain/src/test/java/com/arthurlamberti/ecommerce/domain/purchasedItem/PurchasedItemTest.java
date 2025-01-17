package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchasedItemTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewPurchasedItem_shouldInstantiate() {
        final var item = Fixture.uuid();
        final var sellerId = Fixture.uuid();
        final var qty = Fixture.positiveNumber();
        final var value = Fixture.ItemFixture.price();

        final var actualPurchasedItem = PurchasedItem.newPurchasedItem(sellerId,item,qty,value);
        assertNotNull(actualPurchasedItem.getId());
        assertEquals(qty, actualPurchasedItem.getQty());
        assertEquals(value, actualPurchasedItem.getValue());
        assertNotNull(actualPurchasedItem.getCreatedAt());
        assertNotNull(actualPurchasedItem.getUpdatedAt());
        assertEquals(actualPurchasedItem.getCreatedAt(), actualPurchasedItem.getUpdatedAt());
        assertNull(actualPurchasedItem.getDeletedAt());
    }

    @Test
    public void givenInvalidNullItem_whenCallNewPurchasedItem_shouldReceiveAnError() {
        final String item = null;
        final var sellerId = Fixture.uuid();
        final var qty = Fixture.positiveNumber();
        final var value = Fixture.ItemFixture.price();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'item' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> PurchasedItem.newPurchasedItem(sellerId,item,qty,value)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidValueLessThan0_whenCallNewPurchasedItem_shouldReceiveAnError() {
        final var item = Fixture.uuid();
        final var sellerId = Fixture.uuid();
        final var qty = Fixture.positiveNumber();
        final var value = Fixture.negativeNumber().doubleValue();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'value' should be greater than 0";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> PurchasedItem.newPurchasedItem(sellerId,item,qty,value)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullPurchaseId_whenCallNewPurchasedItem_shouldReceiveAnError() {
        final var item = Fixture.uuid();
        final String sellerId = null;
        final var qty = Fixture.positiveNumber();
        final var value = Fixture.ItemFixture.price();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'purchaseId' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> PurchasedItem.newPurchasedItem(sellerId,item,qty,value)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyPurchaseId_whenCallNewPurchasedItem_shouldReceiveAnError() {
        final var item = Fixture.uuid();
        final var sellerId = "";
        final var qty = Fixture.positiveNumber();
        final var value = Fixture.ItemFixture.price();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'purchaseId' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> PurchasedItem.newPurchasedItem(sellerId,item,qty,value)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidQtyLessThan0_whenCallNewPurchasedItem_shouldReceiveAnError() {
        final var item = Fixture.uuid();
        final var purchaseId = Fixture.uuid();
        final var qty = Fixture.negativeNumber();
        final var value = Fixture.ItemFixture.price();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'qty' should be greater than 0";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> PurchasedItem.newPurchasedItem(purchaseId,item,qty,value)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }
}