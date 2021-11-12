package com.praisesystem.backend.source;

import com.praisesystem.backend.source.model.Source;

public interface SourceService {
    Source createOrUpdate(SourceDto dto);
}
