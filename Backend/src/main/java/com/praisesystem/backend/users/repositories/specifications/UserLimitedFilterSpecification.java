package com.praisesystem.backend.users.repositories.specifications;

import com.praisesystem.backend.accounts.enums.PlatformType;
import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.accounts.model.Account_;
import com.praisesystem.backend.users.dto.request.UserLimitedFilter;
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
public class UserLimitedFilterSpecification implements Specification<UserEntity> {

    UserLimitedFilter filter;

    @Override
    public Predicate toPredicate(@NonNull Root<UserEntity> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        if (filter == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getDiscordTag() != null || filter.getTelegramHandle() != null) {
            Join<UserEntity, Account> accountJoin = root.join(UserEntity_.accounts);
            if (filter.getDiscordTag() != null) {
                predicates.add(builder.and(
                        builder.like(builder.upper(accountJoin.get(Account_.username)), "%" + filter.getDiscordTag().trim().toUpperCase() + "%"),
                        builder.equal(accountJoin.get(Account_.platform), PlatformType.DISCORD)
                ));
            } else {
                predicates.add(builder.and(
                        builder.like(builder.upper(accountJoin.get(Account_.username)), "%" + filter.getTelegramHandle().trim().toUpperCase() + "%"),
                        builder.equal(accountJoin.get(Account_.platform), PlatformType.TELEGRAM)
                ));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
