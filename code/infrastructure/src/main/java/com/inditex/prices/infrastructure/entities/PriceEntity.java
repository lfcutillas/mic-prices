package com.inditex.prices.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "PRICES")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PriceEntity extends AuditEntity {

    @Id
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer brandId;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String curr;

}
