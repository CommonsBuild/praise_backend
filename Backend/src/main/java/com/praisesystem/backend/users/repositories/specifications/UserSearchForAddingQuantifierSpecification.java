package com.praisesystem.backend.users.repositories.specifications;

import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.accounts.model.Account_;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.model.UserEntity_;
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
public class UserSearchForAddingQuantifierSpecification implements Specification<UserEntity> {

    String pattern;

    @Override
    public Predicate toPredicate(@NonNull Root<UserEntity> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        if (pattern == null || pattern.isBlank()) {
            return null;
        }

        query.distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        Join<UserEntity, Account> accountJoin = root.join(UserEntity_.accounts);

        predicates.add(builder.like(builder.upper(root.get(UserEntity_.ethereumAddress)), "%" + pattern.trim().toUpperCase() + "%"));
        predicates.add(builder.like(builder.upper(accountJoin.get(Account_.username)), "%" + pattern.trim().toUpperCase() + "%"));

        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
