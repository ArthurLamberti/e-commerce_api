package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.enums.ItemStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewItem_shouldInstantiateAnItem() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedStatus = ItemStatus.INACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl,expectedPrice, expectedStock);
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedStatus, actualItem.getStatus());
        assertEquals(expectedStock, actualItem.getQtyAvailable());
        assertNotNull(actualItem.getCreatedAt());
        assertNotNull(actualItem.getUpdatedAt());
        assertNull(actualItem.getDeletedAt());
    }

    @Test
    public void givenInvalidNullSellerId_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final String expectedSellerId = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'sellerId' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptySellerId_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedSellerId = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'sellerId' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullName_whenCallNewItem_shouldReturnAnException() {

        final String expectedName = null;
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = "";
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthLessThan10_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.characters(1, 9);
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 10 and 100 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthGreaterThan100_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.characters(101, 1000);
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 10 and 100 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullDescription_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.ItemFixture.name();
        final String expectedDescription = null;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyDescription_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = " ";
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthLessThan50_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.characters(0, 49);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 50 and 1000 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthGreaterThan1000_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.characters(1001, 10000);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 50 and 1000 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullImageUrl_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final String expectedImageUrl = null;
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'image' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyUrl_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = "";
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'image' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenAnInactiveItem_whenCallActivate_shouldReceiveOK() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedStatus = ItemStatus.ACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(ItemStatus.INACTIVE, actualItem.getStatus());
        final var actualCreatedAt = actualItem.getCreatedAt();
        final var actualUpdatedAt = actualItem.getUpdatedAt();

        actualItem.activate();

        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedStock, actualItem.getQtyAvailable());
        assertEquals(expectedStatus, actualItem.getStatus());

        assertEquals(actualItem.getCreatedAt(), actualCreatedAt);
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNull(actualItem.getDeletedAt());
    }

    @Test
    public void givenAnActiveItem_whenCallDeativate_shouldReceiveOK() {
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedStatus = ItemStatus.INACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);
        actualItem.activate();
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(ItemStatus.ACTIVE, actualItem.getStatus());
        final var actualCreatedAt = actualItem.getCreatedAt();
        final var actualUpdatedAt = actualItem.getUpdatedAt();

        actualItem.deactivate();

        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedStock, actualItem.getQtyAvailable());
        assertEquals(expectedStatus, actualItem.getStatus());

        assertEquals(actualItem.getCreatedAt(), actualCreatedAt);
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNotNull(actualItem.getDeletedAt());
        assertEquals(actualItem.getUpdatedAt(), actualItem.getDeletedAt());
    }


    @Test
    public void givenAValidItem_whenCallAddStockWithValidParams_shouldReceiveOK(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var initialStock = Fixture.positiveNumber();
        final var expectedStatus = ItemStatus.ACTIVE;
        final var expectedQtyAvailable = initialStock+10;
        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, initialStock);
        final var actualUpdatedAt = actualItem.getUpdatedAt();

        actualItem.activate();
        actualItem.addStock(10);

        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedStatus, actualItem.getStatus());
        assertEquals(expectedQtyAvailable, actualItem.getQtyAvailable());
        assertNotNull(actualItem.getCreatedAt());
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNull(actualItem.getDeletedAt());
    }


    @Test
    public void givenAValidItem_whenCallAddStockWithInvalidParams_shouldReceiveAnError(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var qtyStock = Fixture.negativeNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Quantity should be positive";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);
        actualItem.activate();

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.addStock(qtyStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenAnInvalidItem_whenCallAddStockWithValidParams_shouldReceiveAnError(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();
        final var qtyStock = Fixture.positiveNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Item should be activate to add stock";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.addStock(qtyStock)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenValidItem_whenCallRemoveStock_shouldDecrementQtyAvailable(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var initialStock = Fixture.positiveNumber()+30;
        final var expectedQtyAvailable = initialStock-10;

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, initialStock);
        final var actualUpdatedAt = actualItem.getUpdatedAt();
        final var actualCreatedAt = actualItem.getCreatedAt();

        actualItem.activate();
        assertEquals(initialStock, actualItem.getQtyAvailable());
        actualItem.removeStock(10);
        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedQtyAvailable, actualItem.getQtyAvailable());
        assertEquals(actualCreatedAt, actualItem.getCreatedAt());
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNull(actualItem.getDeletedAt());
    }

    @Test
    public void givenInvalidNegativeParam_whenCallRemoveStock_shouldReceiveAnError(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Quantity should be positive";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);
        actualItem.activate();
        actualItem.addStock(20);

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.removeStock(Fixture.negativeNumber())
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidQtyGreatherThanQtyAvailable_whenCallRemoveStock_shouldReceiveAnError(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Quantity should be less or equals than qty available";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, 20);
        actualItem.activate();

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.removeStock(30)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidItem_whenCallRemoveStock_shouldReceiveAnError(){
        final var expectedName = Fixture.ItemFixture.name();
        final var expectedDescription = Fixture.ItemFixture.description();
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStock = Fixture.positiveNumber();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Item should be activate to remove stock";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl, expectedPrice, expectedStock);
        actualItem.activate();
        actualItem.addStock(20);
        actualItem.deactivate();

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.removeStock(10)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());}
}
