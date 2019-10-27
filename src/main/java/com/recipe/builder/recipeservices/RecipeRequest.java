package com.recipe.builder.recipeservices;

public class RecipeRequest {

    private String title;

    private String cooktime;

    private String instructions;

    private String description;

    public RecipeRequest(String title, String cooktime, String description, String instructions) {
        this.title = title;
        this.cooktime = cooktime;
        this.instructions = instructions;
        this.description = description;
    }

    public RecipeRequest() {
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

}
