package com.praisesystem.backend.source.services;

import com.praisesystem.backend.source.dto.SourceDto;
import com.praisesystem.backend.source.model.Source;

public interface SourceService {
    Source createOrUpdate(SourceDto dto);
}