package com.praisesystem.backend.users;

import com.praisesystem.backend.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPublicKey(String publicKey);
    List<UserEntity> findByPublicKeyIn(List<String> publicKeys);
}
