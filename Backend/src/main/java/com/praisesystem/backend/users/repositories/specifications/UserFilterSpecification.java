package com.praisesystem.backend.users.repositories.specifications;

import com.praisesystem.backend.common.persistence.BaseSpecification;
import com.praisesystem.backend.users.dto.request.UserFilter;
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
public class UserFilterSpecification extends BaseSpecification<UserEntity> {

    UserFilter filter;

    @Override
    public Predicate toPredicate(@NonNull Root<UserEntity> root, @NonNull  CriteriaQuery<?> query, @NonNull  CriteriaBuilder builder) {
        if (filter == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getEthereumAddress() != null) {
            predicates.add(builder.like(builder.upper(root.get(UserEntity_.ethereumAddress)), "%" + filter.getEthereumAddress().trim().toUpperCase() + "%"));
        }
        if (filter.getDiscordTag() != null) {
            predicates.add(builder.like(builder.upper(root.get(UserEntity_.discordTag)), "%" + filter.getDiscordTag().trim().toUpperCase() + "%"));
        }
        if (filter.getDiscordTag() != null) {
            predicates.add(builder.like(builder.upper(root.get(UserEntity_.telegramHandle)), "%" + filter.getTelegramHandle().trim().toUpperCase() + "%"));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
