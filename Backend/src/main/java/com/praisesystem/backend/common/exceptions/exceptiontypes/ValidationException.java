package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class ValidationException extends BaseRuntimeException {

    public ValidationException(String message) {
        super(ErrorCodes.CLIENT_ERROR, message);
    }

    public ValidationException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
