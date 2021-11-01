package com.praisesystem.backend.users.repositories.specifications;

import com.praisesystem.backend.common.persistence.BaseSpecification;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.model.UserEntity_;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSearchForAddingQuantifierSpecification extends BaseSpecification<UserEntity> {

    String pattern;

    @Override
    public Predicate toPredicate(@NonNull Root<UserEntity> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        if (pattern == null || pattern.isBlank()) {
            return null;
        }

        query.distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.like(builder.upper(root.get(UserEntity_.ethereumAddress)), "%" + pattern.trim().toUpperCase() + "%"));
        predicates.add(builder.like(builder.upper(root.get(UserEntity_.discordTag)), "%" + pattern.trim().toUpperCase() + "%"));
        predicates.add(builder.like(builder.upper(root.get(UserEntity_.telegramHandle)), "%" + pattern.trim().toUpperCase() + "%"));

        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
