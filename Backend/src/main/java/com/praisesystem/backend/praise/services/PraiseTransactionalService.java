package com.praisesystem.backend.praise.services;

import com.praisesystem.backend.praise.dto.CreatePraiseDto;
import com.praisesystem.backend.praise.model.Praise;

import java.util.List;

public interface PraiseTransactionalService {
    Praise findById(Long id);
    List<Praise> createPraise(CreatePraiseDto dto);

}
