package com.praisesystem.backend.users.roles.mapper;

import com.praisesystem.backend.users.roles.mapper.support.RoleToString;
import com.praisesystem.backend.users.roles.mapper.support.RolesToStringList;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface RoleMapper {

    @RoleToString
    default String roleToString(RoleEntity role) {
        return role.getCode().name();
    }

    @RolesToStringList
    default List<String> rolesSetToStringList(Collection<RoleEntity> roles) {
        return new ArrayList<>(roles).stream().map(this::roleToString).collect(Collectors.toList());
    }
}
