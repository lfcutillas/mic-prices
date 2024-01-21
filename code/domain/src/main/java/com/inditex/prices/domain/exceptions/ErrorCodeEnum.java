package com.inditex.prices.domain.exceptions;

public enum ErrorCodeEnum {

    PRICE_NOT_FOUND("E001"),
    RATE_NOT_CALCULABLE("E002");

    private final String code;

    ErrorCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
