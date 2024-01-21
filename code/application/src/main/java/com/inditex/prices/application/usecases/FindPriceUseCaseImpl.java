package com.inditex.prices.application.usecases;

import com.inditex.prices.application.mappers.PriceMapper;
import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.domain.exceptions.PriceNotFoundException;
import com.inditex.prices.domain.exceptions.RateNotCalculableException;
import com.inditex.prices.domain.ports.PricePort;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.domain.usecases.FindPriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindPriceUseCaseImpl implements FindPriceUseCase {

    private final PricePort port;
    private final PriceMapper mapper;

    @Override
    public FinalPrice findBetweenDates(ProductId productId, BrandId brandId, ApplicationDate applicationDate) {

        Collection<Price> prices = port.findBetweenDates(productId, brandId, applicationDate);
        log.debug("A set of [{}] prices was retrieved.", prices.size());

        if (prices.isEmpty()) {
            throw new PriceNotFoundException(productId, brandId);
        }

        Price price = this.findPriceByPriority(prices).orElseThrow(() -> new RateNotCalculableException(productId, brandId));
        return mapper.toResponse(price);

    }

    private Optional<Price> findPriceByPriority(Collection<Price> prices) {
        return prices.stream().reduce((accumulator, price) -> {
            log.debug("Calculating price priority between ids: [{}] vs [{}]", price.getId(), accumulator.getId());
            if (price.getPriority() > accumulator.getPriority()) {
                return price;
            }
            return accumulator;
        });
    }

}
