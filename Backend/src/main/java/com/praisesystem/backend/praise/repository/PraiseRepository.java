package com.praisesystem.backend.praise.repository;

import com.praisesystem.backend.praise.model.Praise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraiseRepository extends JpaRepository<Praise, Long> {
}
