package com.praisesystem.backend.users.model;

import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.codec.binary.Hex;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(name = "ethereum_address", unique = true, nullable = false)
    String ethereumAddress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Set<Account> accounts;

    @Column(name = "nonce", nullable = false)
    String nonce;

//    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    Set<RoleEntity> roles = new HashSet<>();

//    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Set<Score> scoreStats;

    public UserEntity(String ethereumAddress) {
        this.ethereumAddress = ethereumAddress;
    }

    public void updateNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        nonce = Hex.encodeHexString(newNonce);
    }

    public void addRole(RoleEntity role) {
        this.getRoles().add(role);
    }
}
