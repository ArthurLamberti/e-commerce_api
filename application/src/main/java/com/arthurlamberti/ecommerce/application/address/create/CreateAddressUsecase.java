package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.application.UseCase;

public sealed abstract class CreateAddressUsecase
        extends UseCase<CreateAddressCommand, CreateAddressOutput>
        permits DefaultCreateAddressUsecase {

}
