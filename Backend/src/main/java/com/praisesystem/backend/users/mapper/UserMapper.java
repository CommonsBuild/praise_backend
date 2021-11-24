package com.praisesystem.backend.users.mapper;

import com.praisesystem.backend.accounts.mapper.AccountMapper;
import com.praisesystem.backend.users.dto.response.UserLimitedDto;
import com.praisesystem.backend.users.roles.mapper.RoleMapper;
import com.praisesystem.backend.users.roles.mapper.support.RolesToStringList;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleMapper.class, AccountMapper.class}
)
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedBy = RolesToStringList.class)
    UserDto toUserDto(UserEntity entity);

    List<UserDto> toUserDtos(List<UserEntity> userEntities);

    default UserEntity toNewUserFromEthereumAddressAndRoles(String ethereumAddress, List<RoleEntity> roles) {
        if (ethereumAddress == null) {
            return null;
        }
        UserEntity user = new UserEntity(ethereumAddress);
        if (roles != null && !roles.isEmpty()) {
            user.getRoles().addAll(roles);
        }
        user.updateNonce();
        return user;
    }

    @Mapping(target = "roles", source = "roles", qualifiedBy = RolesToStringList.class)
    UserLimitedDto toLimitedUserDto(UserEntity user);

    List<UserLimitedDto> toUserLimitedDtos(List<UserEntity> userEntities);

}
