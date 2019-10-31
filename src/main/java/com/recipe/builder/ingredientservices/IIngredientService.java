package com.recipe.builder.ingredientservices;


import com.recipe.builder.models.Ingredient;
import com.recipe.builder.models.Recipe;

import java.util.List;
import java.util.Optional;


public interface IIngredientService {

    Ingredient create(IngredientRequest ingredientRequest, Recipe recipe);

    Optional<Ingredient> findById(Long id);

    List<Ingredient> getIngredients(Recipe recipe);

    void delete(Ingredient ingredient);

}
