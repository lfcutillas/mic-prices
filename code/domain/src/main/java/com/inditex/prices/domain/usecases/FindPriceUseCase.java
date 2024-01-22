package com.inditex.prices.domain.usecases;

import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;

public interface FindPriceUseCase {

    FinalPrice findBetweenDates(ProductId productId, BrandId brandId, ApplicationDate applicationDate);
}
