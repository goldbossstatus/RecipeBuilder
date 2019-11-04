package com.recipe.thymeleaf.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "rc_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<UserPrivilege> privileges;

    public UserRole() {
    }

    public UserRole(String role_user) {
        this.name = role_user;
    }
}