package com.praisesystem.backend.users.mapper;

import com.praisesystem.backend.roles.mapper.RoleMapper;
import com.praisesystem.backend.roles.mapper.support.RolesToStringList;
import com.praisesystem.backend.roles.model.RoleEntity;
import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleMapper.class}
)
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedBy = RolesToStringList.class)
    UserDto toUserDto(UserEntity entity);

    default UserEntity toNewUserFromPublicKeyAndRoles(String publicKey, List<RoleEntity> roles) {
        if (publicKey == null) {
            return null;
        }
        UserEntity user = new UserEntity(publicKey);
        if (roles != null && !roles.isEmpty()) {
            user.getRoles().addAll(roles);
        }
        user.updateNonce();
        return user;
    }
}
