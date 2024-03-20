package com.digitalcoffee.user.dto.response;

import com.digitalcoffee.commons.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    @Builder.Default
    List<UserRole> roles = new ArrayList<>(0);
}
