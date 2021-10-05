package com.praisesystem.backend.auth;

import com.praisesystem.backend.common.exceptions.Precondition;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessageNonceException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessagePublicKeyException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.MessageSignatureException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.ValidationException;
import lombok.NoArgsConstructor;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;

@NoArgsConstructor
public class AuthParamsValidator {

    static final String ETHEREUM_PREFIX = "0x";

    public static void validateMessagePublicKey(String message, String publicKey) {
        Precondition.ifFalseThrow(message.contains(publicKey),
                new MessagePublicKeyException("The public key in the message does not match the user's public key"));
    }

    public static void validateMessageNonce(String message, String nonce) {
        Precondition.ifFalseThrow(message.contains(nonce),
                new MessageNonceException("The nonce in the message does not match the user's nonce"));
    }

    public static void validateMessageSignature(String publicKey, String message, String signature) {
        Precondition.ifFalseThrow(publicKey.equals(getPubKeyFromMessageAndSignature(message, signature)),
                new MessageSignatureException("Access denied"));
    }

    public static String getPubKeyFromMessageAndSignature(String message, String signature) {
        Precondition.ifTrueThrow(message == null || message.isEmpty(), new ValidationException("Message can not be empty"));
        Precondition.ifTrueThrow(signature == null || signature.isEmpty(), new ValidationException("Signature can not be empty"));

        try {
            byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
            byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
            byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);
            byte[] v = Arrays.copyOfRange(signatureBytes, 64, signatureBytes.length);

            Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);
            BigInteger recoveredPubKey = Sign.signedPrefixedMessageToKey(message.getBytes(), signatureData);
            return Keys.toChecksumAddress(ETHEREUM_PREFIX + Keys.getAddress(recoveredPubKey));
        } catch (SignatureException e) {
            throw new MessageSignatureException(e.getMessage());
        }
    }
}
