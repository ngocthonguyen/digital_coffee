package com.digitalcoffee.user.entity;

import com.digitalcoffee.commons.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dc_user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private UserRoot user;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
