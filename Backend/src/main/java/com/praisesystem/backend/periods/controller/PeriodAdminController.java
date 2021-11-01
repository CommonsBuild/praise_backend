package com.praisesystem.backend.periods.controller;

import com.praisesystem.backend.auth.AuthUtils;
import com.praisesystem.backend.periods.dto.request.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.response.PeriodDto;
import com.praisesystem.backend.periods.services.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Period controller (ADMIN)")
@Slf4j(topic = "[PERIOD ADMIN CONTROLLER]")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/admin/periods")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodAdminController {

    PeriodService periodService;

    @GetMapping(value = "/all")
    @Operation(description = "Find all periods", security = @SecurityRequirement(name = "jwt"))
    public List<PeriodDto> findAll() {
        Long adminId = AuthUtils.getCurrentUser().getId();
        log.info("Request to search for the all periods from user with ID ({})", adminId);
        return periodService.findAllPeriods();
    }

    @GetMapping(value = "/current")
    @Operation(description = "Find last period", security = @SecurityRequirement(name = "jwt"))
    public PeriodDto findLastPeriod() {
        Long adminId = AuthUtils.getCurrentUser().getId();
        log.info("Request to search for the last period from user with ID ({})", adminId);
        return periodService.findLastPeriod();
    }

    @PostMapping(value = "/create")
    @Operation(description = "Create new period", security = @SecurityRequirement(name = "jwt"))
    public PeriodDto createPeriod(@Valid @RequestBody CreatePeriodRequestDto dto) {
        Long adminId = AuthUtils.getCurrentUser().getId();
        log.info("A new request to create a period from a user with an ID ({})", adminId);
        return periodService.create(dto);
    }
}
