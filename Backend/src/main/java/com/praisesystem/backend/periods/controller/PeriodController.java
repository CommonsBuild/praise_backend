package com.praisesystem.backend.periods.controller;

import com.praisesystem.backend.auth.AuthUtils;
import com.praisesystem.backend.periods.dto.request.CreatePeriodRequestDto;
import com.praisesystem.backend.periods.dto.response.PeriodDto;
import com.praisesystem.backend.periods.services.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Period controller")
@Slf4j(topic = "[PERIOD CONTROLLER]")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/periods")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PeriodController {

    PeriodService periodService;

    @Operation(description = "Find all periods", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeriodDto> findAll() {
        return periodService.findAllPeriods();
    }

    @Operation(description = "Find last period", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public PeriodDto findLastPeriod() {
        return periodService.findLastPeriod();
    }

}
