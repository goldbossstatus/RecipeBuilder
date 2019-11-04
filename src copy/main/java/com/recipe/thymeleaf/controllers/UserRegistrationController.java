package com.recipe.thymeleaf.controllers;

import com.recipe.thymeleaf.userservices.UserRequest;
import com.recipe.thymeleaf.userservices.UserServiceAdapter;
import com.recipe.thymeleaf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private RestTemplate template;


    @Autowired
    private UserServiceAdapter userServiceAdapter;

    @ModelAttribute("user")
    public UserRequest userRegistrationDto() {
        return new UserRequest();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRequest userDto,
                                      BindingResult result) {
        template.getForEntity("http://localhost:8080/api/user?email="+userDto.getEmail(), User.class);

        User existing = userServiceAdapter.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userServiceAdapter.save(userDto);
        return "redirect:/registration?success";
    }

}
