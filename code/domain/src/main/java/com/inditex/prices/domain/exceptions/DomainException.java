package com.inditex.prices.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainException extends RuntimeException {

    protected final DomainError error;

    protected DomainException(DomainError error) {
        super(error.getMessage());
        this.error = error;
    }

}
