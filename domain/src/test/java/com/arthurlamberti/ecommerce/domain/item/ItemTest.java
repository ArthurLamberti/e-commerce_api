package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.seller.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewItem_shouldInstantiateAnItem(){
        final var expectedName = Fixture.name();
        final var expectedDescription = Fixture.characters(11, 300);
        final var expectedStatus = Status.INACTIVE;
        final var expectedImageUrl = Fixture.imageUrl();
        final var expectedSellerId = Fixture.uuid();
        final var expectedQty = 0;

        final var actualItem = Item.newItem(expectedSellerId, expectedName,expectedDescription, expectedImageUrl);
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
        assertNotNull(actualItem.getDeletedAt());
    }

    public void givenInvalidNullSellerId_whenCallNewItem_shouldReturnAnException(){
        
    }
    public void givenInvalidEmptySellerId_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidNullName_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidEmptyName_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidNameWithLengthLessThan10_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidNameWithLengthGreaterThan100_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidNullDescription_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidEmptyDescription_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidDescriptionWithLengthLessThan50_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidDescriptionWithLengthGreaterThan1000_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidNullImageUrl_whenCallNewItem_shouldReturnAnException(){}
    public void givenInvalidEmptyUrl_whenCallNewItem_shouldReturnAnException(){}
    public void givenAnInactiveItem_whenCallActivate_shouldReceiveOK(){}
    public void givenAnActiveItem_whenCallDeativate_shouldReceiveOK(){}

}
