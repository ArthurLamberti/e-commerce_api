package com.arthurlamberti.ecommerce.application.address.update;

import com.arthurlamberti.ecommerce.application.UseCase;

public sealed abstract class UpdateAddressUsecase
        extends UseCase<UpdateAddressCommand, UpdateAddressOutput>
        permits DefaultUpdateAddressUsecase {
}
