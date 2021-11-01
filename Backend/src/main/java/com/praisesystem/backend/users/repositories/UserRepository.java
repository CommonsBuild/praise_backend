package com.praisesystem.backend.users.repositories;

import com.praisesystem.backend.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByEthereumAddress(String ethereumAddress);
    List<UserEntity> findByEthereumAddressIn(List<String> ethereumAddresss);
    Set<UserEntity> findUserEntitiesByIdIn(Set<Long> ids);

    @Query("select u.id from UserEntity u")
    List<Long> getAllIds();
}
