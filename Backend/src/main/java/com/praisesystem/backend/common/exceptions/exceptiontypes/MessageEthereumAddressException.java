package com.praisesystem.backend.common.exceptions.exceptiontypes;

import com.praisesystem.backend.common.exceptions.BaseRuntimeException;
import com.praisesystem.backend.common.exceptions.ErrorCodes;

public class MessageEthereumAddressException extends BaseRuntimeException{

    public MessageEthereumAddressException(String message) {
        super(ErrorCodes.ACCESS_DENIED, message);
    }

    public MessageEthereumAddressException(ErrorCodes errorCode, String message) {
        super(errorCode, message);
    }
}
