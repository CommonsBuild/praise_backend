package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class MessageSignatureException extends BaseRuntimeException {

    public MessageSignatureException(String message) {
        super(ErrorCodes.ACCESS_DENIED, message);
    }

    public MessageSignatureException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
