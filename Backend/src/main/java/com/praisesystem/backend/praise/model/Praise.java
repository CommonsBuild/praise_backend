package com.praisesystem.backend.praise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "praises")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Praise extends BaseEntity {

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Period period;

    @JsonBackReference
    @OneToMany(mappedBy = "praise", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<QuantifiedPraise> quantifiedPraises = new HashSet<>();

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
