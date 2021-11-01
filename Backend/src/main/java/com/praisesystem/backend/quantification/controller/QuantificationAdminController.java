package com.praisesystem.backend.quantification.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "[QUANTIFICATION ADMIN CONTROLLER]")
@RestController
@RequestMapping("/api/quantification/quantifier")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuantificationAdminController {
}
