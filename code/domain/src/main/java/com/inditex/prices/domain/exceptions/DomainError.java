package com.inditex.prices.domain.exceptions;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class DomainError implements Serializable {

    private String code;
    private String message;

}
