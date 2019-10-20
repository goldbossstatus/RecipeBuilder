package com.recipe.builder.recipeservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;

public interface IRecipeService {

    Recipe createRecipe(RecipeRequest recipeRequest, User currentUser);

}
