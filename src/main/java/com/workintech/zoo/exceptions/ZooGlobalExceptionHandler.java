package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException ex) {
        log.error("ZooException: status={}, message={}",
                ex.getHttpStatus().value(), ex.getMessage());

        ZooErrorResponse body = new ZooErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleAll(Exception ex) {
        log.error("Unhandled exception", ex);
        ZooErrorResponse body = new ZooErrorResponse(
                500,
                "Internal server error",
                System.currentTimeMillis()
        );
        return ResponseEntity.status(500).body(body);
    }
}
