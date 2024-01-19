package com.inditex.prices.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinalPrice {

    private Integer productId;

    private Integer brandId;

    private BigDecimal finalPrice;

    private BigDecimal rateToApply;

    private String startDate;

    private String endDate;

}
