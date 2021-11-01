package com.praisesystem.backend.quantification.controller;

import com.praisesystem.backend.auth.AuthUtils;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import com.praisesystem.backend.quantification.services.QuantificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j(topic = "[QUANTIFICATION QUANTIFIER CONTROLLER]")
@RestController
@RequestMapping("/api/quantification/quantifier")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuantificationController {

    QuantificationService quantificationService;

    @Operation(description = "Find period quantification info", security = @SecurityRequirement(name = "jwt"))
    @GetMapping("/periodQuantificationInfo")
    public List<QuantifiedPraise> getStatsByPeriod(@RequestParam("periodId") Long periodId) {
        Long userId = AuthUtils.getCurrentUser().getId();
        log.info("Request for get quant stats by period ({}) from user ({})", periodId, userId);
        return quantificationService.getPeriodQuantificationInfo(userId, periodId);
    }
}
