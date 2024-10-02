package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.enums.PurchaseStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewPurchase_shouldInstantiate() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedPurchaseStatus = PurchaseStatus.NEW;
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var actualPurchase = Purchase.newPurchase(
                expectedSeller,
                expectedCustomer,
                expectedListItems,
                expectedTotalValue,
                expectedTotalQty,
                expectedShipping,
                expectedAddress
        );

        assertNotNull(actualPurchase);
        assertNotNull(actualPurchase.getId());
        assertEquals(expectedSeller, actualPurchase.getSeller());
        assertEquals(expectedCustomer, actualPurchase.getCustomer());
        assertEquals(expectedListItems, actualPurchase.getItems());
        assertEquals(expectedTotalQty, actualPurchase.getTotalQty());
        assertEquals(expectedTotalValue, actualPurchase.getTotalValue());
        assertEquals(expectedShipping,actualPurchase.getShipping());
        assertEquals(expectedPurchaseStatus, actualPurchase.getStatus());
        assertNotNull(actualPurchase.getCreatedAt());
        assertEquals(actualPurchase.getCreatedAt(), actualPurchase.getUpdatedAt());
        assertNull(actualPurchase.getDeletedAt());
    }

    @Test
    public void givenInvalidNullSeller_whenCallNewPurchase_shouldReceiveAnError() {
        final Seller expectedSeller = null;
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(Fixture.uuid());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(Fixture.uuid(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'seller' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullCustomer_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final Customer expectedCustomer = null;
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'customer' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullItems_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final List<PurchasedItem> expectedListItems = null;
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'items' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidEmptyItemsList_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final List<PurchasedItem>  expectedListItems = List.of();
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'items' should has at least one item";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidTotalValueLessThan0_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.negativeNumber().doubleValue();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'totalValue' should be greater than 0";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidTotalQtyLessThan0_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.negativeNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'totalQty' should be greater than 0";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullShipping_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final Shipping expectedShipping = null;
        final var expectedAddress = Fixture.AddressFixture.validAddress();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'shipping' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

    @Test
    public void givenInvalidNullAddress_whenCallNewPurchase_shouldReceiveAnError() {
        final var expectedSeller = Fixture.SellerFixture.validSeller();
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var auxItem = Fixture.ItemFixture.validItem(expectedSeller.getId().getValue());
        final var expectedListItems = List.of(Fixture.PurchaseItemFixture.validPurchaseItem(expectedSeller.getId().getValue(),auxItem));
        final var expectedTotalValue = Fixture.ItemFixture.price();
        final var expectedTotalQty = Fixture.positiveNumber();
        final var expectedShipping = Fixture.ShippingFixture.validShipping();
        final Address expectedAddress = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'address' should not be null";

        final var actualError = assertThrows(
                NotificationException.class,
                () -> Purchase.newPurchase(
                        expectedSeller,
                        expectedCustomer,
                        expectedListItems,
                        expectedTotalValue,
                        expectedTotalQty,
                        expectedShipping,
                        expectedAddress
                )
        );

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getFirstError().get().message());
    }

}