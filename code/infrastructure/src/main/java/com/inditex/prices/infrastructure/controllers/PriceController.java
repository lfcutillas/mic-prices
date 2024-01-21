package com.inditex.prices.infrastructure.controllers;

import com.inditex.prices.api.PricesApi;
import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.domain.usecases.FindPriceUseCase;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapper;
import com.inditex.prices.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final FindPriceUseCase findPriceUseCase;
    private final PriceInfraMapper mapper;

    @Override
    public ResponseEntity<PriceResponse> findPrice(Integer productId, Integer brandId, OffsetDateTime applicationDate) {
        FinalPrice finalPrice = findPriceUseCase.findBetweenDates(new ProductId(productId), new BrandId(brandId),
                new ApplicationDate(applicationDate.toLocalDateTime()));
        return ResponseEntity.ok(mapper.toResponse(finalPrice));
    }

}
