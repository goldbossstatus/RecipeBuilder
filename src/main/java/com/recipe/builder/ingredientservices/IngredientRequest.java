package com.recipe.builder.ingredientservices;


public class IngredientRequest {

    private String name;

    public IngredientRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
