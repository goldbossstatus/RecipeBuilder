package com.recipe.recipeservices;

import com.recipe.models.Recipe;
import com.recipe.models.User;
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
    public RecipeDTO save(RecipeDTO recipeDTO, User user) {
        Recipe recipe = new Recipe(recipeDTO.getTitle(),
                recipeDTO.getCooktime(),
                recipeDTO.getInstructions(),
                recipeDTO.getDescription(),
                user);
        Recipe recipeSaved = recipeRepository.save(recipe);
        recipeDTO.setId(recipeSaved.getId());
        return recipeDTO;
    }

    @Override
    public ArrayList<Recipe> findByUserId(Long userId) {
        return recipeRepository.findByUserId(userId);
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        Recipe recipe = optRecipe.get();
        return recipe;
    }
}
