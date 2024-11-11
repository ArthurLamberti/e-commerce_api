package com.arthurlamberti.ecommerce.application.address.retrieve.list;

import com.arthurlamberti.ecommerce.application.UseCase;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;

public abstract class ListAddressUsecase
        extends UseCase<SearchQuery, Pagination<ListAddressOutput>> {
}
