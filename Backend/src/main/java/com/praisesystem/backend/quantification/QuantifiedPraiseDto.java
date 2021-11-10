package com.praisesystem.backend.quantification;

import lombok.Value;

@Value
public class QuantifiedPraiseDto {

    Long periodId;
    String periodName;

    Long praiseId;

    String giverName;
    String recipientName;

    String quantifierName;

    Long score;
}
