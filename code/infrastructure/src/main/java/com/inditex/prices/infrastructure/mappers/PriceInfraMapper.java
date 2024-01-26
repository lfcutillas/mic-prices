package com.inditex.prices.infrastructure.mappers;

import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.infrastructure.entities.PriceEntity;
import com.inditex.prices.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceInfraMapper {

    PriceResponse toResponse(FinalPrice finalPrice);

    Price toDomain(PriceEntity entity);

}
