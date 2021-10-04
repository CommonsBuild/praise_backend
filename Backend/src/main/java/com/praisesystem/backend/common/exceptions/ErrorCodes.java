package com.praisesystem.backend.common.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCodes {
    CLIENT_ERROR(400, "Client Error"),
    SERVER_ERROR(500, "Internal Server Error"),
    ACCESS_DENIED(403, "Access Denied"),
    NOT_FOUND(404, "Not Found");

    int code;

    String message;
}
