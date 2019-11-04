package com.recipe.thymeleaf.controllers;

import com.recipe.thymeleaf.models.Recipe;
import com.recipe.thymeleaf.models.Tag;
import com.recipe.thymeleaf.models.User;
import com.recipe.thymeleaf.recipeservices.RecipeServiceAdapter;
import com.recipe.thymeleaf.tagservices.TagRequest;
import com.recipe.thymeleaf.tagservices.TagServiceAdapter;
import com.recipe.thymeleaf.userservices.UserServiceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/tag")
public class TagController {

    private final TagServiceAdapter tagServiceAdapter;
    private final UserServiceAdapter userServiceAdapter;
    private final RecipeServiceAdapter recipeServiceAdapter;

    public TagController(TagServiceAdapter tagServiceAdapter,
                         UserServiceAdapter userServiceAdapter,
                         RecipeServiceAdapter recipeServiceAdapter) {
        this.tagServiceAdapter = tagServiceAdapter;
        this.userServiceAdapter = userServiceAdapter;
        this.recipeServiceAdapter = recipeServiceAdapter;
    }

    @GetMapping("/add/{id}")
    public String getAddTagForm(@PathVariable("id") Long recipeId,
                                Principal principal, Model model) {
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Recipe> optRecipe = recipeServiceAdapter.findById(recipeId);
        if(optRecipe.get().getUser() == user && optRecipe.isPresent()) {
            Recipe recipe = optRecipe.get();
            model.addAttribute("recipe", recipe);
            return "tag/add";
        } else {
            return "redirect:notfound";
        }
    }

    @PostMapping("/add/{id}")
    public String saveTag(@PathVariable("id") Long recipeId,
                          Principal principal, Model model,
                          TagRequest tagRequest) {
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Recipe> optRecipe = recipeServiceAdapter.findById(recipeId);
        if(optRecipe.get().getUser() == user && optRecipe.isPresent()) {
            Recipe recipe = optRecipe.get();
            Tag tag = tagServiceAdapter.create(tagRequest, recipe);
            return "redirect:/recipe/" + recipe.getId();
        }
        return "redirect:notfound";
    }

    @GetMapping("/{id}")
    public String deleteTag(@PathVariable("id") Long tagId, Principal principal) {
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Tag> optTag = tagServiceAdapter.findById(tagId);
        Optional<Recipe> optRecipe = recipeServiceAdapter.findById(optTag.get().getRecipe().getId());
        if(optRecipe.get().getUser() == user && optRecipe.isPresent()) {
            tagServiceAdapter.delete(optTag.get());
        }
        return "redirect:/recipe/" + optRecipe.get().getId();
    }
}