package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewItem_shouldInstantiateAnItem() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedStatus = Status.INACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQty = 0;

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedStatus, actualItem.getStatus());
        assertEquals(expectedQty, actualItem.getQtyAvailable());
        assertEquals(expectedQty, actualItem.getQtySold());
        assertEquals(expectedQty, actualItem.getQtyReviews());
        assertEquals(expectedQty, actualItem.getScoreReview());
        assertNotNull(actualItem.getCreatedAt());
        assertNotNull(actualItem.getUpdatedAt());
        assertNull(actualItem.getDeletedAt());
    }

    @Test
    public void givenInvalidNullSellerId_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final String expectedSellerId = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'sellerId' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptySellerId_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'sellerId' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullName_whenCallNewItem_shouldReturnAnException() {

        final String expectedName = null;
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = "";
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthLessThan10_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.characters(0, 9);
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 10 and 100 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNameWithLengthGreaterThan100_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.characters(101, 1000);
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 10 and 100 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullDescription_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.name();
        final String expectedDescription = null;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyDescription_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.name();
        final var expectedDescription = " ";
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthLessThan50_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(0, 49);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 50 and 1000 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidDescriptionWithLengthGreaterThan1000_whenCallNewItem_shouldReturnAnException() {

        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(1001, 10000);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 50 and 1000 characters";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullImageUrl_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final String expectedImageUrl = null;
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'image' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyUrl_whenCallNewItem_shouldReturnAnException() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = "";
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'image' should not be empty";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenAnInactiveItem_whenCallActivate_shouldReceiveOK() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedStatus = Status.ACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQty = 0;

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(Status.INACTIVE, actualItem.getStatus());
        final var actualCreatedAt = actualItem.getCreatedAt();
        final var actualUpdatedAt = actualItem.getUpdatedAt();

        actualItem.activate();

        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedQty, actualItem.getQtyAvailable());
        assertEquals(expectedQty, actualItem.getQtySold());
        assertEquals(expectedQty, actualItem.getQtyReviews());
        assertEquals(expectedQty, actualItem.getScoreReview());
        assertEquals(expectedStatus, actualItem.getStatus());

        assertEquals(actualItem.getCreatedAt(), actualCreatedAt);
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNull(actualItem.getDeletedAt());
    }

    @Test
    public void givenAnActiveItem_whenCallDeativate_shouldReceiveOK() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedStatus = Status.INACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQty = 0;

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);
        actualItem.activate();
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(Status.ACTIVE, actualItem.getStatus());
        final var actualCreatedAt = actualItem.getCreatedAt();
        final var actualUpdatedAt = actualItem.getUpdatedAt();

        actualItem.deactivate();

        assertEquals(expectedName, actualItem.getName());
        assertEquals(expectedDescription, actualItem.getDescription());
        assertEquals(expectedImageUrl, actualItem.getImageUrl());
        assertEquals(expectedQty, actualItem.getQtyAvailable());
        assertEquals(expectedQty, actualItem.getQtySold());
        assertEquals(expectedQty, actualItem.getQtyReviews());
        assertEquals(expectedQty, actualItem.getScoreReview());
        assertEquals(expectedStatus, actualItem.getStatus());

        assertEquals(actualItem.getCreatedAt(), actualCreatedAt);
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertNotNull(actualItem.getDeletedAt());
        assertEquals(actualItem.getUpdatedAt(), actualItem.getDeletedAt());
    }

    @Test
    public void givenAnActiveItem_whenCallAddReview_shouldIncrementReviewQtyAndScore() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQtyAvailable = 0;
        final var expectedQtySold = 0;
        final var expectedQtyReview = 1;
        final var expectedScore = 3;


        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);
        actualItem.activate();
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(expectedQtyAvailable, actualItem.getQtyAvailable());
        assertEquals(expectedQtySold, actualItem.getQtySold());
        assertEquals(0, actualItem.getQtyReviews());
        assertEquals(0, actualItem.getScoreReview());

        actualItem.addReview(3);
        final var actualScore = actualItem.getReviewScore();
        final var actualUpdatedAt = actualItem.getUpdatedAt();
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertEquals(expectedScore, actualScore);
        assertEquals(expectedQtyReview, actualItem.getQtyReviews());
    }

    @Test
    public void givenAnActiveItem_whenAddSomeReviews_shouldIncrementReviewQtyAndScore() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQtyAvailable = 0;
        final var expectedQtySold = 0;
        final var expectedQtyReview = 4;
        final var expectedScore = 2.75;


        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);
        actualItem.activate();
        assertNotNull(actualItem);
        assertNotNull(actualItem.getId());
        assertEquals(expectedQtyAvailable, actualItem.getQtyAvailable());
        assertEquals(expectedQtySold, actualItem.getQtySold());
        assertEquals(0, actualItem.getQtyReviews());
        assertEquals(0, actualItem.getScoreReview());

        actualItem.addReview(3);
        actualItem.addReview(1);
        actualItem.addReview(2);
        actualItem.addReview(5);
        final var actualScore = actualItem.getReviewScore();
        final var actualUpdatedAt = actualItem.getUpdatedAt();
        assertTrue(actualItem.getUpdatedAt().isAfter(actualUpdatedAt));
        assertEquals(expectedScore, actualScore);
        assertEquals(expectedQtyReview, actualItem.getQtyReviews());
    }

    @Test
    public void givenAnActiveItem_whenCallAddReviewIfInvalidScore0_shouldReturnAnError() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Review score must be between 1 and 5";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.addReview(0)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenAnActiveItem_whenCallAddReviewIfInvalidScore6_shouldReturnAnError() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Review score must be between 1 and 5";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.addReview(6)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenAnInactiveItem_whenCallAddReview_shouldReceiveAnError() {
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Item should be activate to add review";

        final var actualItem = Item.newItem(expectedSellerId, expectedName, expectedDescription, expectedImageUrl);

        final var actualError = assertThrows(
                NotificationException.class,
                () -> actualItem.addReview(3)
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    public void givenAValidItem_whenCallAddStockWithValidParams_shouldReceiveOK(){

    }

    public void givenAValidItem_whenCallAddStockWithInvalidParams_shouldReceiveAnError(){

    }

    public void givenAnInvalidItem_whenCallAddStockWithValidParams_shouldReceiveAnError(){

    }
    public void givenAValidItem_whenCallSoldItemWithValidParams_shouldReceiveOK(){

    }


    public void givenAValidItem_whenCallSoldItemWithInvalidParams_shouldReceiveAnError(){

    }

    public void givenAnInvalidItem_whenCallSoldItemWithValidParams_shouldReceiveAnError(){

    }
}
