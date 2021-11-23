package com.praisesystem.backend.praise;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.services.PraiseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PRAISE CREATION CONTROLLER !!FOR TEST ONLY!!")
@RestController
@RequestMapping("/api/praise/create")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseTestController {

    PraiseService service;

    @PostMapping("/discord")
    public List<PraiseDto> createDiscordPraise(@RequestBody CreatePraiseDto dto) {
        return service.createPraise(dto);
    }
}
