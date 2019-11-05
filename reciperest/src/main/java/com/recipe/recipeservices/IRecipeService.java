package com.recipe.recipeservices;

import com.recipe.models.Recipe;
import com.recipe.models.User;

import java.util.ArrayList;

public interface IRecipeService {

    RecipeDTO save(RecipeDTO recipeDTO, User currentUser);

    ArrayList<Recipe> findByUserId(Long userId);

    Recipe findById(Long id);
}
