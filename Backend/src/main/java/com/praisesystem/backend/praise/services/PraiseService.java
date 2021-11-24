package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.dto.PraiseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PraiseService {

    Page<PraiseDto> findAllByFilter(PraiseFilter filter, Pageable pageable);

    PraiseDto findById(Long id);

    /**
     * Create praise
     * @param dto CreatePraise Object
     * @return List of created Praises
     */
    List<PraiseDto> createPraise(CreatePraiseDto dto);

}
