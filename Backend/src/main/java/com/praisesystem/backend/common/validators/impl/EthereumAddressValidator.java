package com.praisesystem.backend.common.validators.impl;

import com.praisesystem.backend.common.validators.EthereumAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EthereumAddressValidator implements ConstraintValidator<EthereumAddress, String> {
    public void initialize(EthereumAddress constraint) {
    }

    public boolean isValid(String address, ConstraintValidatorContext context) {
       return address.matches("^0x[a-fA-F0-9]{40}$");
    }
}
