package com.recipe.ingredientservices;


public class IngredientDTO {

    private Long id;
    private String name;
    private String recipeName;
    private Long recipeId;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public IngredientDTO() {
    }

    public IngredientDTO(String name) {
        this.name = name;
    }

    public IngredientDTO(Long id, String name, String recipeName, Long recipeId) {
        this.id = id;
        this.name = name;
        this.recipeName = recipeName;
        this.recipeId = recipeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
