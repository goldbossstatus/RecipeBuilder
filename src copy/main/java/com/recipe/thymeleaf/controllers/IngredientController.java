package com.recipe.thymeleaf.controllers;

import com.recipe.thymeleaf.ingredientservices.IngredientRequest;
import com.recipe.thymeleaf.ingredientservices.IngredientServiceAdapter;
import com.recipe.thymeleaf.models.Ingredient;
import com.recipe.thymeleaf.models.Recipe;
import com.recipe.thymeleaf.models.User;
import com.recipe.thymeleaf.recipeservices.RecipeServiceAdapter;
import com.recipe.thymeleaf.userservices.UserServiceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(value = "/ingredient")
public class IngredientController {

    private final RecipeServiceAdapter recipeServiceAdapter;
    private final IngredientServiceAdapter ingredientServiceAdapter;
    private final UserServiceAdapter userServiceAdapter;

    public IngredientController(RecipeServiceAdapter recipeServiceAdapter,
                                IngredientServiceAdapter ingredientServiceAdapter,
                                UserServiceAdapter userServiceAdapter) {
        this.recipeServiceAdapter = recipeServiceAdapter;
        this.ingredientServiceAdapter = ingredientServiceAdapter;
        this.userServiceAdapter = userServiceAdapter;
    }

    @GetMapping("/add/{id}")
    public String getAddIngredientForm(@PathVariable("id") Long recipeId,
                                       Model model, Principal principal) {
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Recipe> optRecipe = recipeServiceAdapter.findById(recipeId);
        if(optRecipe.get().getUser() == user && optRecipe.isPresent()) {
            Recipe recipe = optRecipe.get();
            model.addAttribute("recipe", recipe);
            return "ingredient/add";
        } else {
            return "redirect:notfound";
        }
    }


    @PostMapping("/add/{id}")
    public String saveIngredient(@PathVariable("id") Long recipeId,
                                Principal principal,
                                Model model,
                                IngredientRequest ingredientRequest) {
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Recipe> optRecipe = recipeServiceAdapter.findById(recipeId);
        if(optRecipe.get().getUser() == user && optRecipe.isPresent()) {
            Recipe recipe = optRecipe.get();
            Ingredient ingredient = ingredientServiceAdapter.create(ingredientRequest, recipe);
            return "redirect:/recipe/" + recipe.getId();
        }
        return "redirect:notfound";
    }

    @GetMapping("/{id}")
    public String deleteIngredient(@PathVariable("id") Long id, Principal principal) {
        Optional<Ingredient> byId = ingredientServiceAdapter.findById(id);
        User user = userServiceAdapter.retrieveUser(principal);
        Optional<Recipe> recipe = recipeServiceAdapter.findById(byId.get().getRecipe().getId());
        if (byId.isPresent() && byId.get().getRecipe().getUser().equals(user)) {
            ingredientServiceAdapter.delete(byId.get());
        }
        return "redirect:/recipe/" + recipe.get().getId();
    }


}
