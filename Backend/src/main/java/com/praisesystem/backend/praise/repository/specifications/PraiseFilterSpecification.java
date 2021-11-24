package com.praisesystem.backend.praise.repository.specifications;

import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.periods.model.Period_;
import com.praisesystem.backend.praise.dto.PraiseFilter;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.praise.model.Praise_;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PraiseFilterSpecification implements Specification<Praise> {

    PraiseFilter filter;

    @Override
    public Predicate toPredicate(@NonNull Root<Praise> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        if (filter == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getPraiseId() != null) {
            predicates.add(builder.equal(root.get(Praise_.id), filter.getPraiseId()));
        }
        if (filter.getPeriodId() != null) {
            Join<Praise, Period> periodJoin = root.join(Praise_.period, JoinType.INNER);
            predicates.add(builder.equal(periodJoin.get(Period_.id), filter.getPeriodId()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
