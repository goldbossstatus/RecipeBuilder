package com.recipe.builder.userservices;

import com.recipe.builder.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;


public interface IUserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);
    User findByEmail(String email);
    User retrieveUser(Principal principal);

}
