package com.recipe.builder.tagservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.Tag;

public interface ITagService {
    Tag create(TagRequest tagRequest, Recipe recipe);
}
