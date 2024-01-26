package com.inditex.prices.infrastructure.repositories;

import com.inditex.prices.infrastructure.entities.PriceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceInfraRepositoryImpl implements PriceInfraRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<PriceEntity> findBetweenDates(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return entityManager.createQuery(
                "SELECT p FROM PRICES p " +
                    "WHERE p.productId = :productId " +
                    "AND p.brandId = :brandId " +
                    "AND p.startDate <= :applicationDate " +
                    "AND p.endDate >= :applicationDate " +
                    "ORDER BY p.priority DESC", PriceEntity.class)
                .setParameter("productId", productId)
                .setParameter("brandId", brandId)
                .setParameter("applicationDate", applicationDate)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
