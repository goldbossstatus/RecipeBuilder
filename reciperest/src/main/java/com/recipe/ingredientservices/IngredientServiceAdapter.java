package com.recipe.ingredientservices;

import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IngredientServiceAdapter implements IIngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceAdapter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDTO create(IngredientDTO ingredientDTO, Recipe recipe) {
        Ingredient ingredient = new Ingredient(
                ingredientDTO.getName(),
                recipe);
        Ingredient saved = ingredientRepository.save(ingredient);
        ingredientDTO.setId(saved.getId());
        ingredientDTO.setRecipeId(ingredient.getRecipe().getId());
        ingredientDTO.setRecipeName(ingredient.getRecipe().getTitle());
        return ingredientDTO;
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> getIngredients(Recipe recipe) {
        return recipe.getIngredients();
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
