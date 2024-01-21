package com.inditex.prices.infrastructure.repositories;

import com.inditex.prices.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;

public interface PriceInfraRepository extends JpaRepository<PriceEntity, Long> {

    @Query("select p from PRICES p where p.brandId = :brandId and p.productId = :productId "
        + "and p.startDate <= :applicationDate and p.endDate >= :applicationDate")
    Collection<PriceEntity> findBetweenDates(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
