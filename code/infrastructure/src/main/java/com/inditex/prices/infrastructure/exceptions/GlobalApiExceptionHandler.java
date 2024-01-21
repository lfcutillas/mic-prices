package com.inditex.prices.infrastructure.exceptions;

import com.inditex.prices.domain.exceptions.DomainException;
import com.inditex.prices.infrastructure.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<ErrorResponseDTO> customServletExceptionHandler(DomainException e, WebRequest request) {

        log.debug("ResourceNotFoundException", e);

        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .timestamp(LocalDateTime.now().toString())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

}
