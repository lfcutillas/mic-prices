package com.inditex.prices.domain.exceptions;

import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;

public class RateNotCalculableException extends DomainException {

    public RateNotCalculableException(ProductId productId, BrandId brandId) {
        super(DomainError.builder()
            .code(ErrorCodeEnum.RATE_NOT_CALCULABLE.getCode())
            .message(String.format("It is not possible calculate rate priority for product [%s] with brand [%s]",
                productId.value(), brandId.value()))
            .build());
    }

}
