package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.enums.ShippingStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShippingTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewShipping_thenShouldInstantiate(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedCode = Fixture.characters(1,19);
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedStatus = ShippingStatus.CREATED;

        final var actualShipping = Shipping.newShipping(expectedAddress, expectedCode, expectedPrice);

        assertNotNull(actualShipping.getId());
        assertEquals(expectedAddress, actualShipping.getAddress());
        assertEquals(expectedCode, actualShipping.getCode());
        assertEquals(expectedPrice, actualShipping.getPrice());
        assertEquals(expectedStatus, actualShipping.getStatus());
        assertNotNull(actualShipping.getCreatedAt());
        assertNotNull(actualShipping.getUpdatedAt());
        assertEquals(actualShipping.getCreatedAt(), actualShipping.getUpdatedAt());
        assertNull(actualShipping.getDeletedAt());
    }

    @Test
    public void givenInvalidNullAddress_whenCallNewShipping_thenShouldReceiveAnError(){
        final Address expectedAddress = null;
        final var expectedCode = Fixture.characters(1,19);
        final var expectedPrice = Fixture.ItemFixture.price();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'item' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Shipping.newShipping(expectedAddress, expectedCode, expectedPrice)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullCodeParam_whenCallnewShipping_thenShouldReceiveAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final String expectedCode = null;
        final var expectedPrice = Fixture.ItemFixture.price();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'code' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Shipping.newShipping(expectedAddress, expectedCode, expectedPrice)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

    }

    @Test
    public void givenInvalidEmptyCodeParam_whenCallNewShipping_thenShouldReceiveAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedCode = " ";
        final var expectedPrice = Fixture.ItemFixture.price();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'code' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Shipping.newShipping(expectedAddress, expectedCode, expectedPrice)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

    }

    @Test
    public void givenInvalidCodeParamWithLengthGreaterThanLimit_whenCallNewShipping_thenShouldReceiveAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedCode = Fixture.characters(20);
        final var expectedPrice = Fixture.ItemFixture.price();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'code' length should be less than 20";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Shipping.newShipping(expectedAddress, expectedCode, expectedPrice)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

    }

    @Test
    public void givenInvalidPriceParam_whenCallNewShipping_thenShouldReceiveAnError(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedCode = Fixture.characters(1,19);
        final var expectedPrice = Fixture.negativeNumber().doubleValue();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'price' should be greater than 0";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> Shipping.newShipping(expectedAddress, expectedCode, expectedPrice)
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAValidShipping_whenCallUpdateStatus_ShouldReturnOK(){
        final var expectedAddress = Fixture.AddressFixture.validAddress();
        final var expectedCode = Fixture.characters(1,19);
        final var expectedPrice = Fixture.ItemFixture.price();
        final var expectedInitialStatus = ShippingStatus.CREATED;
        final var expectedStatus = ShippingStatus.DELIVERED;

        final var actualShipping = Shipping.newShipping(expectedAddress, expectedCode, expectedPrice);

        assertNotNull(actualShipping);
        assertNotNull(actualShipping.getId());
        assertEquals(expectedInitialStatus, actualShipping.getStatus());

        actualShipping.changeStatus(expectedStatus);

        assertEquals(expectedAddress, actualShipping.getAddress());
        assertEquals(expectedCode, actualShipping.getCode());
        assertEquals(expectedPrice, actualShipping.getPrice());
        assertEquals(expectedStatus, actualShipping.getStatus());
        assertNotNull(actualShipping.getCreatedAt());
        assertNotNull(actualShipping.getUpdatedAt());
        assertTrue(actualShipping.getCreatedAt().isBefore(actualShipping.getUpdatedAt()));
        assertNull(actualShipping.getDeletedAt());
    }
}