package com.pablom.imatiaorders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalErrorHandler {
    private static final String MESSAGE = "message";
    private static final String STATUS_CODE = "status-code";
    private static final String URI = "uri";
    private static final String METHOD = "method";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerRuntimeException(
            final HttpServletRequest request, final RuntimeException e) {

        Map<String, String> map = new HashMap<>();
        map.put(METHOD, request.getMethod());
        map.put(URI, request.getRequestURI());
        map.put(MESSAGE, Objects.requireNonNull(e.getMessage()));
        map.put(STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

}
