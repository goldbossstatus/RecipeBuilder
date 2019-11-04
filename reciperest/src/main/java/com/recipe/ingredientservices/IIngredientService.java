package com.recipe.ingredientservices;


import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;

import java.util.List;
import java.util.Optional;


public interface IIngredientService {

    Ingredient create(IngredientRequest ingredientRequest, Recipe recipe);

    Optional<Ingredient> findById(Long id);

    List<Ingredient> getIngredients(Recipe recipe);

    void delete(Ingredient ingredient);

}
