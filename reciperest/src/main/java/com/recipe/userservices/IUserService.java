package com.recipe.userservices;

import com.recipe.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;


public interface IUserService extends UserDetailsService {

    UserDTO saveAPI(UserDTO userDTO);
    User findByEmail(String email);
    User retrieveUser(Principal principal);

}
