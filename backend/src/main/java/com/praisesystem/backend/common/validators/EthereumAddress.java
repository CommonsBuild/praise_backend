package com.praisesystem.backend.common.validators;

import com.praisesystem.backend.common.validators.impl.EthereumAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EthereumAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EthereumAddress {
    String message() default "Invalid Ethereum address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
