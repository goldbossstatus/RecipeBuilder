package com.recipe.builder.tagservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.Tag;

import java.util.ArrayList;
import java.util.Optional;

public interface ITagService {
    Tag create(TagRequest tagRequest, Recipe recipe);

    void delete(Tag tag);

    Optional<Tag> findById(Long tagId);

    ArrayList<Tag> findByRecipeId(Long recipeId);
}
