package com.recipe.builder.ingredientservices;

import com.recipe.builder.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    ArrayList<Ingredient> findByRecipeId (Long recipeId);
}

