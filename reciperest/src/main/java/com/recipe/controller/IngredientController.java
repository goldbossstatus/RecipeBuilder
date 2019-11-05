package com.recipe.controller;

import com.recipe.ingredientservices.IIngredientService;
import com.recipe.ingredientservices.IngredientDTO;
import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;
import com.recipe.models.User;
import com.recipe.recipeservices.IRecipeService;
import com.recipe.userservices.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recipe/{id}")
public class IngredientController {

    private final IUserService userServiceAdapter;
    private final IRecipeService recipeServiceAdapter;
    private final IIngredientService ingredientServiceAdapter;

    public IngredientController(IUserService userServiceAdapter,
                                IRecipeService recipeServiceAdapter,
                                IIngredientService ingredientServiceAdapter) {
        this.userServiceAdapter = userServiceAdapter;
        this.recipeServiceAdapter = recipeServiceAdapter;
        this.ingredientServiceAdapter = ingredientServiceAdapter;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getAllByRecipeId(@PathVariable("id") Long id,
                                             Principal principal) {
        User currentUser = userServiceAdapter.retrieveUser(principal);
        Recipe recipe = recipeServiceAdapter.findById(id);
        if(recipe.getUser().getId() == currentUser.getId()) {
            return recipe.getIngredients();
        }
        // return customerror
        return null;
    }

    @PostMapping("/ingredients")
    public ResponseEntity<IngredientDTO> addIngredient(@PathVariable("id") Long id,
                                                       Principal principal,
                                                       @RequestBody IngredientDTO ingredientDTO) {
        User currentUser = userServiceAdapter.retrieveUser(principal);
        Recipe recipe = recipeServiceAdapter.findById(id);
        IngredientDTO ingredientDTO1 = ingredientServiceAdapter.create(ingredientDTO, recipe);
        if(recipe.getUser() == currentUser) {
            return ResponseEntity.created(URI.create("api/recipe/"+id+"/ingredients/"+ingredientDTO1.getId())).body(ingredientDTO1);
        }
        // return customerror
        return null;
    }

    @DeleteMapping("/ingredients/{ingredientId}")
    public List<Ingredient> deleteIngredient(@PathVariable("id") Long id,
                                                      @PathVariable("ingredientId") Long ingredientId,
                                                      Principal principal) {
        User currentUser = userServiceAdapter.retrieveUser(principal);
        Recipe recipe = recipeServiceAdapter.findById(id);
        Optional<Ingredient> optIngredient = ingredientServiceAdapter.findById(ingredientId);
        if(optIngredient.isPresent() && recipe.getUser() == currentUser) {
            ingredientServiceAdapter.delete(optIngredient.get());
            return recipe.getIngredients();
        }
        // return customerror;
        return null;
    }

}

