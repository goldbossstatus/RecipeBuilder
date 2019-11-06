package com.recipe.recipeservices;

import com.recipe.ingredientservices.IngredientRepository;
import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;
import com.recipe.models.Tag;
import com.recipe.models.User;
import com.recipe.tagservices.TagRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RecipeServiceAdapter implements IRecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final TagRepository tagRepository;

    public RecipeServiceAdapter(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public RecipeDTO save(RecipeDTO recipeDTO, User user) {
        Recipe recipe = new Recipe(recipeDTO.getTitle(),
                recipeDTO.getCooktime(),
                recipeDTO.getInstructions(),
                recipeDTO.getDescription(),
                user);
        Recipe recipeSaved = recipeRepository.save(recipe);
        recipeDTO.setId(recipeSaved.getId());
        recipeDTO.setUserId(recipeSaved.getUser().getId());
        return recipeDTO;
    }

    @Override
    public ArrayList<Recipe> findByUserId(Long userId) {
        return recipeRepository.findByUserId(userId);
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        Recipe recipe = optRecipe.get();
        return recipe;
    }

    @Override
    public Recipe changeTitle(Recipe recipe, RecipeChangeTitleDTO recipeChangeTitleDTO) {

            ArrayList<Ingredient> getIngredients = new ArrayList<>();
            ArrayList<Tag> getTags = new ArrayList<>();

            Recipe newRecipe = new Recipe(recipeChangeTitleDTO.getTitle(), recipe.getCooktime(),
                recipe.getInstructions(), recipe.getDescription(),
                recipe.getUser(), getIngredients, getTags);

            for(Ingredient ingredient: recipe.getIngredients()) {
                Ingredient newing = new Ingredient(ingredient.getName(), newRecipe);
                getIngredients.add(newing);
                ingredientRepository.delete(ingredient);
            }

            for(Tag tag: recipe.getTags()) {
                Tag newTag = new Tag(tag.getName(), newRecipe);
                getTags.add(newTag);
                tagRepository.delete(tag);
            }

            recipeRepository.save(newRecipe);
            recipeRepository.delete(recipe);
            return newRecipe;

    }

    @Override
    public Recipe changeCooktime(Recipe recipe, RecipeChangeCooktimeDTO recipeChangeCooktimeDTO) {
        ArrayList<Ingredient> getIngredients = new ArrayList<>();
        ArrayList<Tag> getTags = new ArrayList<>();

        Recipe newRecipe = new Recipe(recipe.getTitle(), recipeChangeCooktimeDTO.getCooktime(),
                recipe.getInstructions(), recipe.getDescription(),
                recipe.getUser(), getIngredients, getTags);

        for(Ingredient ingredient: recipe.getIngredients()) {
            Ingredient newing = new Ingredient(ingredient.getName(), newRecipe);
            getIngredients.add(newing);
            ingredientRepository.delete(ingredient);
        }

        for(Tag tag: recipe.getTags()) {
            Tag newTag = new Tag(tag.getName(), newRecipe);
            getTags.add(newTag);
            tagRepository.delete(tag);
        }

        recipeRepository.save(newRecipe);
        recipeRepository.delete(recipe);
        return newRecipe;
    }

    @Override
    public Recipe changeInstruction(Recipe recipe, RecipeChangeInstructionDTO recipeChangeInstructionDTO) {
        ArrayList<Ingredient> getIngredients = new ArrayList<>();
        ArrayList<Tag> getTags = new ArrayList<>();

        Recipe newRecipe = new Recipe(recipe.getTitle(), recipe.getCooktime(),
                recipeChangeInstructionDTO.getInstructions(), recipe.getDescription(),
                recipe.getUser(), getIngredients, getTags);

        for(Ingredient ingredient: recipe.getIngredients()) {
            Ingredient newing = new Ingredient(ingredient.getName(), newRecipe);
            getIngredients.add(newing);
            ingredientRepository.delete(ingredient);
        }

        for(Tag tag: recipe.getTags()) {
            Tag newTag = new Tag(tag.getName(), newRecipe);
            getTags.add(newTag);
            tagRepository.delete(tag);
        }

        recipeRepository.save(newRecipe);
        recipeRepository.delete(recipe);
        return newRecipe;

    }

    @Override
    public Recipe changeDescription(Recipe recipe, RecipeChangeDescriptionDTO recipeChangeDescriptionDTO) {

        ArrayList<Ingredient> getIngredients = new ArrayList<>();
        ArrayList<Tag> getTags = new ArrayList<>();

        Recipe newRecipe = new Recipe(recipe.getTitle(), recipe.getCooktime(),
                recipe.getInstructions(), recipeChangeDescriptionDTO.getDescription(),
                recipe.getUser(), getIngredients, getTags);

        for(Ingredient ingredient: recipe.getIngredients()) {
            Ingredient newing = new Ingredient(ingredient.getName(), newRecipe);
            getIngredients.add(newing);
            ingredientRepository.delete(ingredient);
        }

        for(Tag tag: recipe.getTags()) {
            Tag newTag = new Tag(tag.getName(), newRecipe);
            getTags.add(newTag);
            tagRepository.delete(tag);
        }

        recipeRepository.save(newRecipe);
        recipeRepository.delete(recipe);
        return newRecipe;
    }

    @Override
    public Recipe changeRecipe(Recipe recipe, RecipeDTO recipeDTO) {

        ArrayList<Ingredient> getIngredients = new ArrayList<>();
        ArrayList<Tag> getTags = new ArrayList<>();

        Recipe newRecipe = new Recipe(recipeDTO.getTitle(), recipeDTO.getCooktime(),
                recipeDTO.getInstructions(), recipeDTO.getDescription(),
                recipe.getUser(), getIngredients, getTags);

        for(Ingredient ingredient: recipe.getIngredients()) {
            Ingredient newing = new Ingredient(ingredient.getName(), newRecipe);
            getIngredients.add(newing);
            ingredientRepository.delete(ingredient);
        }

        for(Tag tag: recipe.getTags()) {
            Tag newTag = new Tag(tag.getName(), newRecipe);
            getTags.add(newTag);
            tagRepository.delete(tag);
        }

        recipeRepository.save(newRecipe);
        recipeRepository.delete(recipe);
        return newRecipe;
    }

}
