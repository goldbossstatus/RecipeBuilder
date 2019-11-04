package com.recipe.thymeleaf.controllers;

import com.recipe.thymeleaf.models.Ingredient;
import com.recipe.thymeleaf.models.Recipe;
import com.recipe.thymeleaf.models.Tag;
import com.recipe.thymeleaf.models.User;
import com.recipe.thymeleaf.recipeservices.RecipeRequest;
import com.recipe.thymeleaf.recipeservices.RecipeServiceAdapter;
import com.recipe.thymeleaf.userservices.UserServiceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/recipe")
public class RecipeController {

    private final UserServiceAdapter userServiceAdapter;
    private final RecipeServiceAdapter recipeServiceAdapter;

    public RecipeController(UserServiceAdapter userServiceAdapter,
                            RecipeServiceAdapter recipeServiceAdapter) {
        this.userServiceAdapter = userServiceAdapter;
        this.recipeServiceAdapter = recipeServiceAdapter;
    }

    @GetMapping("/create")
    public String getCreateRecipeForm() {
        return "recipe/create";
    }

    @PostMapping(value = "/save")
    public String createRecipe(@ModelAttribute RecipeRequest recipeRequest,
                               Principal principal) {
        User user = userServiceAdapter.retrieveUser(principal);
        Recipe recipe = recipeServiceAdapter.save(recipeRequest, user);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String getRecipeListForm(Model model, Principal principal) {
        User user = userServiceAdapter.retrieveUser(principal);
        model.addAttribute("recipes", recipeServiceAdapter.findByUserId(user.getId()));
        return "recipe/list";
    }

    @GetMapping("/{id}")
    public String getRecipeDetailForm(@PathVariable("id") Long id,
                                      Model model,
                                      Principal principal) {

        Optional<Recipe> optionalRecipe = recipeServiceAdapter.findById(id);
        if(optionalRecipe.isPresent()) {
            User user = userServiceAdapter.retrieveUser(principal);
            Recipe recipe = optionalRecipe.get();
            List<Ingredient> ingredients = recipe.getIngredients();
            List<Tag> tags = recipe.getTags();
            model.addAttribute("tags", tags);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("recipe", recipe);
            return "recipe/detail";
        } else {
            return "redirect:notfound";
        }
    }
}
