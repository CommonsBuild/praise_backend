package com.praisesystem.backend.periods.controller;

import com.praisesystem.backend.auth.AuthUtils;
import com.praisesystem.backend.periods.dto.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.PeriodDto;
import com.praisesystem.backend.periods.services.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j(topic = "[PERIOD ADMIN CONTROLLER]")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/admin/period")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodAdminController {

    PeriodService periodService;

    @GetMapping(value = "/current")

    @PostMapping(value = "/create")
    @Operation(description = "Create new period", security = @SecurityRequirement(name = "jwt"))
    public PeriodDto createPeriod(@Valid @RequestBody CreatePeriodRequestDto dto) {
        Long userId = AuthUtils.getCurrentUser().getId();
        log.info("A new request to create a period from a user with an ID ({})", userId);
        return periodService.create(dto);
    }
}
