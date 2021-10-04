package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class MessagePublicKeyException extends BaseRuntimeException {

    public MessagePublicKeyException(String message) {
        super(ErrorCodes.ACCESS_DENIED, message);
    }

    public MessagePublicKeyException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
