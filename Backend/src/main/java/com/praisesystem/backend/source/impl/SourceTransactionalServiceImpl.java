package com.praisesystem.backend.source.impl;

import com.praisesystem.backend.source.SourceDto;
import com.praisesystem.backend.source.SourceRepository;
import com.praisesystem.backend.source.SourceTransactionalService;
import com.praisesystem.backend.source.mapper.SourceMapper;
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
public class SourceTransactionalServiceImpl implements SourceTransactionalService {

    SourceRepository sourceRepository;
    SourceMapper sourceMapper;

    @Override
    public Source createOrUpdate(SourceDto dto) {
        Source source = sourceRepository.findByIdAndPlatform(dto.getId(), dto.getPlatform());

        source = source == null ? sourceMapper.toNewSource(dto) : sourceMapper.updateSource(source, dto);

        return sourceRepository.save(source);
    }
}
