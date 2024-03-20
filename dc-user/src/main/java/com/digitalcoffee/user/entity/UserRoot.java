package com.digitalcoffee.user.entity;

import com.digitalcoffee.commons.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dc_user")
public class UserRoot {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoleEntity> roles = new ArrayList<>(0);

    public void addRoles(List<UserRole> roles) {
        roles.forEach(this::addRole);
    }

    public void addRole(UserRole role){
        this.roles.add(UserRoleEntity.builder().user(this).role(role).build());
    }
}
