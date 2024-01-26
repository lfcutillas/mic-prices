package com.inditex.prices.domain.ports;

import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;

public interface PricePort {

    Price findBetweenDates(ProductId productId, BrandId brandId, ApplicationDate applicationDate);

}
