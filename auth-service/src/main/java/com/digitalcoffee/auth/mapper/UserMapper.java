package com.digitalcoffee.auth.mapper;

import com.degitalcoffee.commons.UserRole;
import com.digitalcoffee.auth.dto.request.RegisterRequest;
import com.digitalcoffee.auth.dto.request.UserUpdateRequest;
import com.digitalcoffee.auth.dto.response.UserResponse;
import com.digitalcoffee.auth.entity.UserRoleEntity;
import com.digitalcoffee.auth.entity.UserRoot;
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
