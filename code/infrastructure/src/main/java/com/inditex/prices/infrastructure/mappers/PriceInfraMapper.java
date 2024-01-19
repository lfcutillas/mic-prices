package com.inditex.prices.infrastructure.mappers;

import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceInfraMapper {

    PriceResponse toResponse(FinalPrice finalPrice);

}
