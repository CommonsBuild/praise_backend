package com.praisesystem.backend.praise;

import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.services.PraiseService;
import com.praisesystem.backend.source.model.Source;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/{id}")
    public Praise getPraiseById(@PathVariable("id") Long id) {
        Source source = new Source();

        return praiseService.findById(id);
    }
}
