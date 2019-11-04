package com.recipe.recipeservices;

import com.recipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    ArrayList<Recipe> findByUserId(Long userId);

}
