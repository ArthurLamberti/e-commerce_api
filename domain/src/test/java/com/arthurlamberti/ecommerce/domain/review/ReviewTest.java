package com.arthurlamberti.ecommerce.domain.review;

import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.UnitTest;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.reviews.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallNewReview_shouldInstantiateAReview() {
        final var expectedCustomerId = Fixture.uuid();
        final var expectedItemId = Fixture.uuid();
        final var expectedDescription = Fixture.characters(1, 200);
        final var expectedRating = Fixture.randomDouble(1, 5);

        final var actualReview = Review.newReview(expectedCustomerId, expectedItemId, expectedDescription, expectedRating);

        assertNotNull(actualReview);
        assertNotNull(actualReview.getId());
        assertEquals(expectedCustomerId, actualReview.getCustomerId());
        assertEquals(expectedItemId, actualReview.getItemId());
        assertEquals(expectedDescription, actualReview.getDescription());
        assertEquals(expectedRating, actualReview.getRating());
    }

    @ParameterizedTest
    @CsvSource({
            ",itemIdTest,descriptionTest,2,customerId should not be null",
            "'',itemIdTest,descriptionTest,2,customerId should not be empty",
            "customerIdTest,,descriptionTest,2,itemId should not be null",
            "customerIdTest,'',descriptionTest,2,itemId should not be empty",
            "customerIdTest,itemIdTest,,2,description should not be null",
            "customerIdTest,itemIdTest,'',2,description should not be empty",
            "customerIdTest,itemIdTest,description,,rating should not be null",
            "customerIdTest,itemIdTest,description,-1,rating should be between 0 and 5",
            "customerIdTest,itemIdTest,description,6,rating should be between 0 and 5"
    })
    public void givenInvalidParam_whenCallsNewReview_shouldReturnAnException(
            final String expectedCustomerId,
            final String expectedItemId,
            final String expectedDescription,
            final Double expectedRating,
            final String errorMessage
    ) {
        final var actualException = assertThrows(
                NotificationException.class,
                () -> Review.newReview(expectedCustomerId, expectedItemId, expectedDescription, expectedRating)
        );

        assertNotNull(actualException);
        assertEquals(1, actualException.getErrors().size());
        assertEquals(errorMessage, actualException.getFirstError().get().message());
    }
}
