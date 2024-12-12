package com.arthurlamberti.ecommerce.application.shipping.create_by_purchase;

import com.arthurlamberti.ecommerce.application.UseCase;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;

public abstract class CreateShippingUseCase
        extends UseCase<CreateShippingByPurchaseCommand, Shipping> {
}
