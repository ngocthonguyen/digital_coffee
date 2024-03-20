package com.digitalcoffee.user.mapper;

import com.digitalcoffee.commons.UserRole;
import com.digitalcoffee.user.dto.request.RegisterRequest;
import com.digitalcoffee.user.dto.request.UserUpdateRequest;
import com.digitalcoffee.user.dto.response.UserResponse;
import com.digitalcoffee.user.entity.UserRoleEntity;
import com.digitalcoffee.user.entity.UserRoot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserRoot toUser(RegisterRequest request);

    default UserRole map(UserRoleEntity role){
        return role.getRole();
    }

    UserResponse toUserResponse(UserRoot userRoot);

    void updateUser(@MappingTarget UserRoot userRoot, UserUpdateRequest request);
}
