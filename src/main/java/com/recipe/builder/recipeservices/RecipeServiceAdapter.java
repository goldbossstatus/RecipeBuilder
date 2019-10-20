package com.recipe.builder.recipeservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeServiceAdapter implements IRecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceAdapter(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe createRecipe(RecipeRequest recipeRequest, User currentUser) {
        Recipe recipe = new Recipe(recipeRequest.getTitle(),
                recipeRequest.getCooktime(),
                recipeRequest.getInstructions(),
                recipeRequest.getDescription(),
                currentUser);
        recipeRepository.save(recipe);
        return recipe;
    }
}
