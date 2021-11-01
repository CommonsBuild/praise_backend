package com.praisesystem.backend.praise.impl;

import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;
import com.praisesystem.backend.praise.services.PraiseService;
import com.praisesystem.backend.praise.services.PraiseTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseServiceImpl implements PraiseService {

    PraiseTransactionalService praiseTransactionalService;

    @Override
    public List<TelegramPraiseDto> createTelegramPraise(CreateTelegramPraiseDto dto) {
        return praiseTransactionalService.createTelegramPraise(dto);
    }
}
