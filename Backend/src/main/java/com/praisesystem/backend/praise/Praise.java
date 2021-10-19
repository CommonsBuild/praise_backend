package com.praisesystem.backend.praise;

import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.PeriodEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "praises")
@Entity
public class Praise extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id")
    PeriodEntity period;

    @OneToMany(mappedBy = "praise", fetch = FetchType.LAZY)
    List<QuantifiedPraise> quantifiedPraises;

    @Column(name = "reason", columnDefinition = "text", nullable = false)
    String reason;

    @Column(name = "discord_channel_name")
    String discordChannelName;

    @Column(name = "giver_discord_tag")
    String giverDiscordTag;

    @Column(name = "recipient_discord_tag")
    String recipientDiscordTag;

    @Column(name = "telegram_channel_id")
    String telegramChannelId;

    @Column(name = "giver_telegram_id")
    String giverTelegramId;

    @Column(name = "recipient_telegram_id")
    String recipientTelegramId;
}
