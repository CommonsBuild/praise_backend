package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.dto.PraiseFilter;
import com.praisesystem.backend.praise.services.PraiseService;
import com.praisesystem.backend.praise.services.PraiseTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseServiceImpl implements PraiseService {

    PraiseTransactionalService praiseTransactionalService;

    @Override
    public Page<PraiseDto> findAllByFilter(PraiseFilter filter, Pageable pageable) {
        return praiseTransactionalService.findAllByFilter(filter, pageable);
    }

    @Override
    public PraiseDto findById(Long id) {
        return praiseTransactionalService.findById(id);
    }

    @Override
    public List<PraiseDto> createPraise(CreatePraiseDto dto) {
        return praiseTransactionalService.createPraise(dto);
    }
}
