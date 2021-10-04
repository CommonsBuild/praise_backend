package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class JwtAuthenticationException extends BaseRuntimeException {

    public JwtAuthenticationException(String message) {
        super(ErrorCodes.ACCESS_DENIED, message);
    }

    public JwtAuthenticationException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }

    public static void throwMe(String message) {
        throw new JwtAuthenticationException(message);
    }
}
