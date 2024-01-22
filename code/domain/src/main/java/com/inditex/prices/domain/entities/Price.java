package com.inditex.prices.domain.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Price {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer brandId;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String curr;

}
