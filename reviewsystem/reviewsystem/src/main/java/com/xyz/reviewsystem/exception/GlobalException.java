package com.xyz.reviewsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(WebRequest request,Exception e) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);

    }


}
