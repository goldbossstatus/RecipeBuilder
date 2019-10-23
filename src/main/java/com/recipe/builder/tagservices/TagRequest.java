package com.recipe.builder.tagservices;

import com.recipe.builder.models.Recipe;

public class TagRequest {

    private String title;
    private Recipe recipe;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
