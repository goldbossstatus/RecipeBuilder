package com.recipe.builder.userservices;

import com.recipe.builder.models.User;
import com.recipe.builder.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceAdapter implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                                userRegistrationDto.getEmail(),
                                passwordEncoder().encode(userRegistrationDto.getPassword()));
        List<UserRole> roles = new ArrayList<UserRole>();
        roles.add(new UserRole("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        List<User> byEmail = userRepository.findByEmail(email);
        return byEmail.size() > 0 ? byEmail.get(0) : null;
    }

    @Override
    public User retrieveUser(Principal principal) {
        return this.findByEmail(principal.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        // https://github.com/scratches/jpa-method-security-sample/blob/master/src/main/java/demo/Application.java
        List<User> myList = userRepository.findByEmail(username);
        if (myList.size() == 0) {
            return null;
        } else {
            User user = myList.get(0);
            List<GrantedAuthority> auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");

            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    auth);

            System.out.println("userDetails :: " + userDetails.toString());
            return userDetails;
        }
    }

}
