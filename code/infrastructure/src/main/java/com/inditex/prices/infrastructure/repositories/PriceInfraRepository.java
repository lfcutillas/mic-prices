package com.inditex.prices.infrastructure.repositories;

import com.inditex.prices.infrastructure.entities.PriceEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceInfraRepository {

    Optional<PriceEntity> findBetweenDates(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
