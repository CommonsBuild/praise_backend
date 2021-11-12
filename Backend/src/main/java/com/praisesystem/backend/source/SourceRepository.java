package com.praisesystem.backend.source;

import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.source.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, String> {
    Source findByIdAndPlatform(String id, PlatformType platform);
}
