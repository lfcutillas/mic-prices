package com.inditex.prices.application.usecases;

import com.inditex.prices.application.mappers.PriceMapper;
import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.ports.PricePort;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.domain.usecases.FindPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPriceUseCaseImpl implements FindPriceUseCase {

    private final PricePort port;
    private final PriceMapper mapper;

    @Override
    public FinalPrice findBetweenDates(ProductId productId, BrandId brandId, ApplicationDate applicationDate) {
        return mapper.toResponse(port.findBetweenDates(productId, brandId, applicationDate));
    }

}
