package com.praisesystem.backend;

import com.praisesystem.backend.auth.AuthParamsValidator;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
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

    private static String SIGNATURE = "0x80a922517b1b18b9c73874d3b08964beaf23e53ef14ccd5cff8913c9dd9f6cf345a7ace75ae63b303a9cdac2bd0b9421d3c76337fbb26c8b55cba3688fac0bf01c";
    private static final String ADDRESS = "0xf592eE0E3a20Ddd65882E0fE6bFBB4B465A98Ae4";

    @Test
    void test3() throws SignatureException {
        byte[] signature = Numeric.hexStringToByteArray(SIGNATURE);
        byte[] r = Arrays.copyOfRange(signature, 0, 32);
        byte[] s = Arrays.copyOfRange(signature, 32, 64);
        byte[] v = Arrays.copyOfRange(signature, 64, signature.length);

        Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

        BigInteger recoveredPubKey = Sign.signedPrefixedMessageToKey(MESSAGE.getBytes(), signatureData);
        System.out.println("0x" + Keys.getAddress(recoveredPubKey));
        System.out.println(("0x" + Keys.getAddress(recoveredPubKey) + "|"));
        System.out.println(AuthParamsValidator.getPubKeyFromMessageAndSignature(MESSAGE, SIGNATURE) + "|");
    }

    @Test
    void testNonce() {
        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);
        System.out.println(Arrays.toString(nonce));
        System.out.println(Hex.encodeHexString(nonce));
    }

    @Test
    void print() {

    }
}
