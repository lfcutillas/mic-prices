package com.inditex.prices.application.mappers;

import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "id", target = "rateToApply")
    @Mapping(source = "price", target = "finalPrice")
    FinalPrice toResponse(Price price);

}
