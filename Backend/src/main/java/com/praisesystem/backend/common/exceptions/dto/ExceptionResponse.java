package com.praisesystem.backend.common.exceptions.dto;

import lombok.Value;

import java.util.List;

@Value
public class ExceptionResponse {
    int code;
    String uri;
    String message;
    List<String> errors;
}
