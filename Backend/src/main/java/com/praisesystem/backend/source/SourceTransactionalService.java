package com.praisesystem.backend.source;

import com.praisesystem.backend.source.model.Source;

public interface SourceTransactionalService {
    Source createOrUpdate(SourceDto dto);
}
