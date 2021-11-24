package com.praisesystem.backend.quantification;

import com.praisesystem.backend.users.dto.response.UserDto;
import lombok.Value;

@Value
public class QuantifiedPraiseDto {

    Long periodId;

    Long praiseId;

    UserDto quantifier;

    Long score;
}
