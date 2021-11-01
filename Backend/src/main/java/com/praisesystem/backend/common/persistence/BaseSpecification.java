package com.praisesystem.backend.common.persistence;

import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BaseSpecification<T> implements Specification<T> {

    @NonNull
    @Override
    public Specification<T> and(Specification<T> other) {
        return Specification.super.and(other);
    }

    @NonNull
    @Override
    public Specification<T> or(Specification<T> other) {
        return Specification.super.or(other);
    }

    public abstract Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder);
}
