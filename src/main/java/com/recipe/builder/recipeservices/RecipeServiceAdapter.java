package com.recipe.builder.recipeservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RecipeServiceAdapter implements IRecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceAdapter(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe save(RecipeRequest recipeRequest, User currentUser) {
        Recipe recipe = new Recipe(recipeRequest.getTitle(),
                recipeRequest.getCooktime(),
                recipeRequest.getInstructions(),
                recipeRequest.getDescription(),
                currentUser);
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public ArrayList<Recipe> findByUserId(Long userId) {
        return recipeRepository.findByUserId(userId);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }
}
