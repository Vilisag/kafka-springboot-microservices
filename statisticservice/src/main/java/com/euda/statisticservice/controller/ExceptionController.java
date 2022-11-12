package com.euda.statisticservice.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.euda.statisticservice.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> conflictData(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("code", "409");
        response.put("message", "Something went wrong");
        response.put("error", "Conflict data");

        return response;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> methodNotSupportedException(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("code", "405");
        response.put("message", "Something went wrong");
        response.put("error", "Method not allow");

        return response;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequestHandler(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("code", "400");
        response.put("message", "Something went wrong");
        response.put("error", "Params are wrong types");

        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFound(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("code", "404");
        response.put("message", "Something went wrong");
        response.put("error", "Not Found");

        return response;
    }
}
