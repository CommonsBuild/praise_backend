package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.dto.PraiseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PraiseTransactionalService {

    Page<PraiseDto> findAllByFilter(PraiseFilter filter, Pageable pageable);

    PraiseDto findById(Long id);

    List<PraiseDto> createPraise(CreatePraiseDto dto);

}
