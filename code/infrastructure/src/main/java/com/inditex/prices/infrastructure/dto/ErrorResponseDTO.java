package com.inditex.prices.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorResponseDTO implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    private String code;
    private String message;
    private String timestamp;
    private int status;
    private String path;
    private String exception;

}
