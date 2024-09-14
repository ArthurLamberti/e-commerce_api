package com.arthurlamberti.ecommerce.application;

public abstract class NullaryUseCase<OUT> {

    public NullaryUseCase() {

    }

    public abstract OUT execute();

}
