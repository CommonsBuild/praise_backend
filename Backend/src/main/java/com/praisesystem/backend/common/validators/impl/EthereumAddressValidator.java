package com.praisesystem.backend.common.validators.impl;

import com.praisesystem.backend.common.validators.EthereumAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EthereumAddressValidator implements ConstraintValidator<EthereumAddress, String> {
    public void initialize(EthereumAddress constraint) {
    }

    public boolean isValid(String address, ConstraintValidatorContext context) {
        Predicate<String> p1 = x -> x.contains("123");
        Predicate<String> p2 = x -> !x.isBlank();
        Stream.of(p1, p2).reduce(x -> true, Predicate::and);
       return address.matches("^0x[a-fA-F0-9]{40}$");
    }
}
