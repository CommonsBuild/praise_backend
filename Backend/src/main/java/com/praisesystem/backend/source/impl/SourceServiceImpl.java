package com.praisesystem.backend.source.impl;

import com.praisesystem.backend.source.SourceDto;
import com.praisesystem.backend.source.SourceService;
import com.praisesystem.backend.source.SourceTransactionalService;
import com.praisesystem.backend.source.model.Source;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SourceServiceImpl implements SourceService {

    SourceTransactionalService sourceTransactionalService;

    @Override
    public Source createOrUpdate(SourceDto dto) {
        return sourceTransactionalService.createOrUpdate(dto);
    }
}
