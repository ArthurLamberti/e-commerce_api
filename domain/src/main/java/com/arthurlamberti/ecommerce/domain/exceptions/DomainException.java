package com.arthurlamberti.ecommerce.domain.exceptions;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class DomainException extends NoStacktraceException {
    protected final List<Error> errors;

    public DomainException(String aMessage, final List<Error> erros) {
        super(aMessage);
        this.errors = erros;
    }

    public Optional<Error> getFirstError() {
        return Optional.of(errors.get(0));
    }
}
