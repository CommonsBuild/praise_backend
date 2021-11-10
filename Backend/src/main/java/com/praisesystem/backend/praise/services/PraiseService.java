package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.model.Praise;

import java.util.List;

public interface PraiseService {
    /**
     * Create praise from telegram
     * @param dto CreateTelegramPraise Object
     * @return List of new Praises
     */
    List<Praise> createPraise(CreatePraiseDto dto);
}
