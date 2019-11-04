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
    public Ingredient create(IngredientRequest ingredientRequest, Recipe recipe) {
        Ingredient ingredient = new Ingredient(
                ingredientRequest.getName(),
                recipe);
        return ingredientRepository.save(ingredient);
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
