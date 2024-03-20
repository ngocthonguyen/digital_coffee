package com.digitalcoffee.user.dto.request;

import com.digitalcoffee.commons.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    UserRole role;
}
