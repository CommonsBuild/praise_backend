package com.praisesystem.backend.accounts.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformType {
    DISCORD("Discord"),
    TELEGRAM("Telegram");

    String label;
}
