package com.praisesystem.backend.source.model;

import com.praisesystem.backend.accounts.enums.PlatformType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "sources")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Source {

    @Id
    String id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "channel_id")
    String channelId;

    @Column(name = "channel_name")
    String channelName;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    PlatformType platform;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    LocalDateTime updatedAt;
}