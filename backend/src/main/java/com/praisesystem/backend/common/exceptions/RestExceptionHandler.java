package com.praisesystem.backend.common.exceptions;

import com.praisesystem.backend.common.exceptions.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Exception handler for unpredictable exceptions
     * @param e {@link Exception} object
     * @param request {@link HttpServletRequest} object
     * @return {@link ExceptionResponse} object
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse handle(Exception e, HttpServletRequest request) {
        log.error("Exception when calling URI {}. {}", request.getRequestURI(), e.getMessage());
        return new ExceptionResponse(500, request.getRequestURI(), "Internal server error", Collections.emptyList());
    }

    /**
     * Exception handler for most of Runtime errors
     * @param e Exception object inherited from {@link BaseRuntimeException}
     * @param request {@link HttpServletRequest} object
     * @return
     */
    @ExceptionHandler(value = {BaseRuntimeException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handle(BaseRuntimeException e, HttpServletRequest request) {
        log.error("Exception when calling URI {}. {}", request.getRequestURI(), e.getMessage());
        return new ExceptionResponse(e.getCode(), request.getRequestURI(), e.getMessage(), Collections.emptyList());
    }

    /**
     * Exception handler for validation errors
     * @param e {@link Exception} object
     * @param request {@link HttpServletRequest} object
     * @return {@link ExceptionResponse} object
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ExceptionResponse handle(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("Exception when calling URI {}. {}", request.getRequestURI(), e.getMessage());
        List<String> errorList = new ArrayList<>();
        e.getFieldErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
        e.getGlobalErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), "Validation Error", errorList);
    }
}
