package com.praisesystem.backend.periods.repositories;

import com.praisesystem.backend.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodUserRepository extends JpaRepository<UserEntity, Long> {
}
