package com.praisesystem.backend.praise.controller;

import com.praisesystem.backend.praise.dto.CreateTelegramPraiseDto;
import com.praisesystem.backend.praise.dto.TelegramPraiseDto;
import com.praisesystem.backend.praise.services.PraiseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j(topic = "[PRAISE USER CONTROLLER]")
@RestController
@RequestMapping(value = "/api/praise")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseUserController {

    PraiseService praiseService;

    @PostMapping(value = "/createTelegramPraise", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelegramPraiseDto> createTelegramPraise(@Valid @RequestBody CreateTelegramPraiseDto dto) {
        log.info("New praise from telegram user ({}) to recipients ({})", dto.getGiverId(), dto.getRecipientIds());
        return praiseService.createTelegramPraise(dto);
    }

    @PostMapping(value = "/createDiscordPraise", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelegramPraiseDto> createDiscordPraise(@Valid @RequestBody CreateTelegramPraiseDto dto) {
        log.info("New praise from telegram user ({}) to recipients ({})", dto.getGiverId(), dto.getRecipientIds());
        return praiseService.createTelegramPraise(dto);
    }
}
