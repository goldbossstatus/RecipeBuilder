package com.recipe.recipeservices;

import com.recipe.models.Recipe;
import com.recipe.models.User;

import java.util.ArrayList;

public interface IRecipeService {

    RecipeDTO save(RecipeDTO recipeDTO, User currentUser);

    ArrayList<Recipe> findByUserId(Long userId);

    Recipe findById(Long id);

    Recipe changeTitle(Recipe recipe, RecipeChangeTitleDTO recipeChangeTitleDTO);

    Recipe changeCooktime(Recipe recipe, RecipeChangeCooktimeDTO recipeChangeCooktimeDTO);

    Recipe changeInstruction(Recipe recipe, RecipeChangeInstructionDTO recipeChangeInstructionDTO);

    Recipe changeDescription(Recipe recipe, RecipeChangeDescriptionDTO recipeChangeDescriptionDTO);

    Recipe changeRecipe(Recipe recipe, RecipeDTO recipeDTO);

}
