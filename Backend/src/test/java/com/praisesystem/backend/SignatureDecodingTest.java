package com.praisesystem.backend;

import com.praisesystem.backend.auth.AuthParamsValidator;
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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Arrays;

public class SignatureDecodingTest {
    private static final String MESSAGE = "SIGN THIS MESSAGE TO LOGIN TO PRAISE.\n" +
            "\n" +
            "ADDRESS:\n" +
            "0xf592eE0E3a20Ddd65882E0fE6bFBB4B465A98Ae4\n" +
            "\n" +
            "NONCE:\n" +
            "NONCE123";
        
    private static final String MESSAGE_2 = "SIGN THIS MESSAGE TO LOGIN TO PRAISE.\n" +
            "\n" +
            "ADDRESS:\n" +
            "0x0bfBC654e57f9db86D422D5666627dd32b551D55\n" +
            "\n" +
            "NONCE:\n" +
            "NONCE987";

    private static String SIGNATURE = "0x80a922517b1b18b9c73874d3b08964beaf23e53ef14ccd5cff8913c9dd9f6cf345a7ace75ae63b303a9cdac2bd0b9421d3c76337fbb26c8b55cba3688fac0bf01c";
    private static final String ADDRESS = "0xf592eE0E3a20Ddd65882E0fE6bFBB4B465A98Ae4";

    @Test
    void getPubKeyFromMessageAndSignatureTest() throws SignatureException {

        String result_1 = AuthParamsValidator.getPubKeyFromMessageAndSignature(MESSAGE, SIGNATURE);
        Assertions.assertEquals(ADDRESS, result_1);

        String result_2 = AuthParamsValidator.getPubKeyFromMessageAndSignature(MESSAGE_2, SIGNATURE);
        Assertions.assertNotEquals(ADDRESS, result_2);

        /* byte[] signature = Numeric.hexStringToByteArray(SIGNATURE);
        byte[] r = Arrays.copyOfRange(signature, 0, 32);
        byte[] s = Arrays.copyOfRange(signature, 32, 64);
        byte[] v = Arrays.copyOfRange(signature, 64, signature.length);

        Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

        BigInteger recoveredPubKey = Sign.signedPrefixedMessageToKey(MESSAGE.getBytes(), signatureData);
        //System.out.println("0x" + Keys.getAddress(recoveredPubKey));
        //System.out.println(("0x" + Keys.getAddress(recoveredPubKey) + "|"));
        //System.out.println(AuthParamsValidator.getPubKeyFromMessageAndSignature(MESSAGE, SIGNATURE) + "|");
        String gen_address = "0x" + Keys.getAddress(recoveredPubKey);
        Assertions.assertEquals(ADDRESS.toLowerCase(), gen_address); */
    }

   /*  @Test
    void testNonce() {
        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);
        System.out.println(Arrays.toString(nonce));
        System.out.println(Hex.encodeHexString(nonce));
    }
 */
    @Test
    void validateMessageEthereumAddressTest(){
        AuthParamsValidator.validateMessageEthereumAddress(MESSAGE, ADDRESS);
        Assertions.assertThrows(MessageEthereumAddressException.class, () -> {
            AuthParamsValidator.validateMessageEthereumAddress(MESSAGE_2, ADDRESS);
          });
    }

    @Test
    void validateMessageNonceTest() {
        AuthParamsValidator.validateMessageNonce(MESSAGE, "NONCE123");
        Assertions.assertThrows(MessageNonceException.class, () -> {
            AuthParamsValidator.validateMessageNonce(MESSAGE_2, "NONCE123");
        });
    }

    @Test
    void validateMessageSignatureTest() {
       
        AuthParamsValidator.validateMessageSignature(ADDRESS, MESSAGE, SIGNATURE);
        Assertions.assertThrows(MessageSignatureException.class, () -> {
            AuthParamsValidator.validateMessageSignature(ADDRESS, MESSAGE_2, SIGNATURE);
        });
    }

}
