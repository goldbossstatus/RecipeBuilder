package com.recipe.recipeservices;

import com.recipe.models.Ingredient;
import com.recipe.models.Tag;

import java.util.List;

public class RecipeDTO {

    private Long userId;

    private long id;

    private String title;

    private String cooktime;

    private String instructions;

    private String description;

    private List<Tag> tags;

    private List<Ingredient> ingredients;

    public RecipeDTO() {
    }

    public RecipeDTO(long id, String title, String cooktime, String description, String instructions) {
        this.id = id;
        this.title = title;
        this.cooktime = cooktime;
        this.instructions = instructions;
        this.description = description;
    }

    public RecipeDTO(Long id, String title, String cooktime, String description,
                     String instructions, List<Tag> tags,
                     List<Ingredient> ingredients, Long userId) {
        this.id = id;
        this.title = title;
        this.cooktime = cooktime;
        this.description = description;
        this.instructions = instructions;
        this.tags = tags;
        this.ingredients = ingredients;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Long getUserId() {
        return userId;
    }

}
