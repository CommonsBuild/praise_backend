package com.praisesystem.backend.users.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.codec.binary.Hex;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    public UserEntity(String ethereumAddress) {
        this.ethereumAddress = ethereumAddress;
    }

    @Column(name = "ethereum_address", unique = true, nullable = false)
    String ethereumAddress;

    @Column(name = "nonce", nullable = false)
    String nonce;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    Set<RoleEntity> roles = new HashSet<>();

    public void updateNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        nonce = Hex.encodeHexString(newNonce);
    }
}
