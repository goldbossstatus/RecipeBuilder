package com.recipe.builder.UserServices;

import com.recipe.builder.Security.dto.UserRegistrationDto;
import com.recipe.builder.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);
    User findByEmail(String email);

}
