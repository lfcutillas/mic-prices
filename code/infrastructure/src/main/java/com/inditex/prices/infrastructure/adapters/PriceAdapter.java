package com.inditex.prices.infrastructure.adapters;

import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.domain.ports.PricePort;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapper;
import com.inditex.prices.infrastructure.repositories.PriceInfraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class PriceAdapter implements PricePort {

    private final PriceInfraRepository repository;
    private final PriceInfraMapper mapper;

    @Override
    public Collection<Price> findBetweenDates(ProductId productId, BrandId brandId, ApplicationDate applicationDate) {
        return mapper.toDomain(repository.findBetweenDates(productId.value(), brandId.value(), applicationDate.value()));
    }

}
