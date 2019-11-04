package com.recipe.thymeleaf.userservices;

import com.recipe.thymeleaf.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;


public interface IUserService extends UserDetailsService {

    User save(UserRequest userRequest);
    User findByEmail(String email);
    User retrieveUser(Principal principal);
    UserResponse saveAPI(UserRequest userRequest);
}
