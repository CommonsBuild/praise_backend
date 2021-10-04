package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class MessageNonceException extends BaseRuntimeException {

    public MessageNonceException(String message) {
        super(ErrorCodes.ACCESS_DENIED, message);
    }

    public MessageNonceException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
