package com.inditex.prices.domain.exceptions;

import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;

public class PriceNotFoundException extends DomainException {

    public PriceNotFoundException(ProductId productId, BrandId brandId) {
        super(DomainError.builder()
            .code(ErrorCodeEnum.PRICE_NOT_FOUND.getCode())
            .message(String.format("Price for product [%s] with brand [%s] not found.",
                productId.value(), brandId.value()))
            .build());
    }

}
