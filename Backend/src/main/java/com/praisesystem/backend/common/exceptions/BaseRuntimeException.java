package com.praisesystem.backend.common.exceptions;

import lombok.Getter;

@Getter
public abstract class BaseRuntimeException extends RuntimeException {

    private final int code;

    protected BaseRuntimeException(ErrorCodes errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
