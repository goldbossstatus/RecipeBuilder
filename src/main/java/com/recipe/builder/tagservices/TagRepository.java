package com.recipe.builder.tagservices;

import com.recipe.builder.models.Ingredient;
import com.recipe.builder.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    ArrayList<Tag> findByRecipeId (Long recipeId);
}
