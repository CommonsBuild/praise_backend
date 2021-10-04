package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class NotFoundObjectException extends BaseRuntimeException {

    public NotFoundObjectException(String message) {
        super(ErrorCodes.NOT_FOUND, message);
    }

    public NotFoundObjectException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }

    public static void throwMe(String message) {
        throw new NotFoundObjectException(message);
    }
}
