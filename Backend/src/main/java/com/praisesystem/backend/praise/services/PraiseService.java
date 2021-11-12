package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.model.Praise;

import java.util.List;

public interface PraiseService {

    Praise findById(Long id);

    /**
     * Create praise
     * @param dto CreatePraise Object
     * @return List of created Praises
     */
    List<Praise> createPraise(CreatePraiseDto dto);
}
