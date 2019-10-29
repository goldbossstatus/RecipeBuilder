package com.recipe.builder.controllers;

import com.recipe.builder.ingredientservices.IngredientRequest;
import com.recipe.builder.ingredientservices.IngredientServiceAdapter;
import com.recipe.builder.models.Ingredient;
import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import com.recipe.builder.recipeservices.RecipeServiceAdapter;
import com.recipe.builder.userservices.UserServiceAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
//            return "redirect:recipe/detail";
            return "redirect:/recipe/" + recipe.getId();

        }
        return "recipe/notfound";
    }


}
