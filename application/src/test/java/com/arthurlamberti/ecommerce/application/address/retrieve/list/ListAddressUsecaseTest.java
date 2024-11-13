package com.arthurlamberti.ecommerce.application.address.retrieve.list;

import com.arthurlamberti.ecommerce.application.UseCaseTest;
import com.arthurlamberti.ecommerce.domain.Fixture;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ListAddressUsecaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListAddressUsecase usecase;

    @Mock
    private AddressGateway addressGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(addressGateway);
    }

    @Test
    public void givenAValidQuery_whenCallsListAddress_shouldReturnAddress() {
        final var address = List.of(
                Fixture.AddressFixture.validAddressWithZipcode("12312"),
                Fixture.AddressFixture.validAddressWithZipcode("99912")
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "12";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedTotalCount = 2;

        final var expectedItems = address
                .stream()
                .map(ListAddressOutput::from)
                .toList();

        final var expectedPagination = new Pagination<>(
                expectedPage,
                expectedPerPage,
                expectedTotalCount,
                address
        );

        when(addressGateway.findAll(any())).thenReturn(expectedPagination);
        final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var actualOutput = usecase.execute(aQuery);

        assertEquals(expectedPage, actualOutput.currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
        Assertions.assertEquals(expectedTotalCount, actualOutput.total());
        Assertions.assertEquals(expectedItems, actualOutput.items());
    }
}