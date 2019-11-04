package com.recipe.thymeleaf.models;

import javax.persistence.*;

@Entity
@Table(name = "rc_user_privilege")
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // @ManyToMany(mappedBy = "privileges")
    // private Collection<Role> roles;
}