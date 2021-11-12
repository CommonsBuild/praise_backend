package com.praisesystem.backend.accounts.model;

import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    String id;

    @Column(name = "username")
    String username;

    @Column(name = "profile_image_url", columnDefinition = "text")
    String profileImageURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    PlatformType platform;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    LocalDateTime updatedAt;
}