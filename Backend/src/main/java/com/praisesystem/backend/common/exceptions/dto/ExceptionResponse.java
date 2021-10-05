package com.praisesystem.backend.common.exceptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
@Schema(description = "Default exception response")
public class ExceptionResponse {
    @Schema(description = "Code")
    int code;
    @Schema(description = "Request URI")
    String uri;
    @Schema(description = "Error message")
    String message;
    @Schema(description = "Errors list (mainly for validation errors. format is field:error)")
    List<String> errors;
}
