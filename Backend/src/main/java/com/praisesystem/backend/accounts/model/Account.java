package com.praisesystem.backend.accounts.model;

import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;

    @Column(name = "account_id", unique = true, nullable = false)
    String accountId;

    @Column(name = "username")
    String username;

    @Column(name = "profile_image_url", columnDefinition = "text")
    String profileImageURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    PlatformType platform;
}
