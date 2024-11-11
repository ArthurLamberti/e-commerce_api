package com.arthurlamberti.ecommerce.application.address.delete;

import com.arthurlamberti.ecommerce.application.UnitUseCase;

public sealed abstract class DeleteAddressUsecase
    extends UnitUseCase<String>
    permits DefaultDeleteAddressUsecase{
}
