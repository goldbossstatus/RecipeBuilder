package com.recipe.builder.recipeservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;

import java.util.ArrayList;
import java.util.Optional;

public interface IRecipeService {

    Recipe save(RecipeRequest recipeRequest, User currentUser);

    ArrayList<Recipe> findByUserId(Long userId);

    Optional<Recipe> findById(Long id);
}
