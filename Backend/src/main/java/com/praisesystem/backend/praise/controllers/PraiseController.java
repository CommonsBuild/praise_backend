package com.praisesystem.backend.praise.controllers;

import com.praisesystem.backend.praise.dto.PraiseDto;
import com.praisesystem.backend.praise.dto.PraiseFilter;
import com.praisesystem.backend.praise.services.PraiseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/praise")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseController {

    PraiseService praiseService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PraiseDto> getAllPraises(
            @ParameterObject PraiseFilter filter,
            @ParameterObject Pageable pageable
    ) {
        return praiseService.findAllByFilter(filter, pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PraiseDto getPraiseById(@PathVariable("id") Long id) {
        return praiseService.findById(id);
    }
}
