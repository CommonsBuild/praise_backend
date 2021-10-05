package com.praisesystem.backend.users.roles.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCode {
    ROLE_ADMIN("Admin"),
    ROLE_QUANTIFIER("Quantifier"),
    ROLE_USER("User");

    String label;
}
