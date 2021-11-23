package com.praisesystem.backend.source.dto;

import com.praisesystem.backend.accounts.enums.PlatformType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class SourceDto {
    @Schema(description = "Source ID (Discord: ServerID | Telegram: Channel ID)")
    String id;

    @Schema(description = "Source name (Discord: Server name | Telegram: Channel name")
    String name;

    @Schema(description = "Optional discord channel ID", nullable = true)
    String channelId;

    @Schema(description = "Optional discord channel name", nullable = true)
    String channelName;

    @Schema(description = "Platform type", enumAsRef = true)
    PlatformType platform;
}
