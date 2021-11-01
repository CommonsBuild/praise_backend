package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;

import java.util.List;

public interface PraiseTransactionalService {
    List<TelegramPraiseDto> createTelegramPraise(CreateTelegramPraiseDto dto);
}
