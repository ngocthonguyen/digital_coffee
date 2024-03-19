package com.digitalcoffee.auth.entity;

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
@Table(name = "mc_user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private UserRoot user;

    private UserRole role;
}
