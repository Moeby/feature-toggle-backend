package com.moeby.featuretoggle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class.getName());

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void defaultErrorHandler(Exception e) {
        logger.severe(e.getMessage());
    }
}