package com.praisesystem.backend;

import com.praisesystem.backend.common.validators.impl.EthereumAddressValidator;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessageNonceException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessageEthereumAddressException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessageSignatureException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.ValidationException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;


public class EthereumAddressValidatorTest {

    //private static String SIGNATURE = "0x80a922517b1b18b9c73874d3b08964beaf23e53ef14ccd5cff8913c9dd9f6cf345a7ace75ae63b303a9cdac2bd0b9421d3c76337fbb26c8b55cba3688fac0bf01c";
    private static final String CORRECT_CHECKSUM_ADDRESS = "0xf592eE0E3a20Ddd65882E0fE6bFBB4B465A98Ae4";
    private static final String CORRECT_NONCHECKSUM_ADDRESS = "0xf592eE0E3a20Ddd65882E0fE6bFBB4B465A98Ae4";
    private static final String WRONG_ADDRESS = "ABCDeE0E3a20Ddd65882E0fE6bFBB4B465A9WXYZ";


    @Test
    void isValidTest(){
        AuthParamsValidator.validateMessageEthereumAddress(MESSAGE, ADDRESS);
        Assertions.assertThrows(MessageEthereumAddressException.class, () -> {
            AuthParamsValidator.validateMessageEthereumAddress(MESSAGE_2, ADDRESS);
          });
    }

    @Test
    void validateMessageNonceTest() {
        EthereumAddressValidator.isValid(CORRECT_CHECKSUM_ADDRESS);
        EthereumAddressValidator.isValid(CORRECT_NONCHECKSUM_ADDRESS);
        Assertions.assertIsFalse(EthereumAddressValidator.isValid(WRONG_ADDRESS));
    }
}
