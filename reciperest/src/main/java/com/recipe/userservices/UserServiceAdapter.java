package com.recipe.userservices;

import com.recipe.models.User;
import com.recipe.models.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("userServiceAdapter")
public class UserServiceAdapter implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceAdapter(UserRepository userRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDTO saveAPI(UserDTO userDTO) {
        User user = new User(userDTO.getFistName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()));
        List<UserRole> roles = new ArrayList<UserRole>();
        roles.add(new UserRole("ROLE_USER"));
        user.setRoles(roles);
        User userSaved = userRepository.save(user);
        userDTO.setId(userSaved.getId());
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
