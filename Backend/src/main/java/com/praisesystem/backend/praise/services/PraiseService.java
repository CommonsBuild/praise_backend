package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;

import java.util.List;

public interface PraiseService {
    /**
     * Create praise from telegram
     * @param dto CreatePraise Object
     * @return Praise object
     */
    List<TelegramPraiseDto> createTelegramPraise(CreateTelegramPraiseDto dto);
}
