package com.recipe.thymeleaf.ingredientservices;

import com.recipe.thymeleaf.models.Ingredient;
import com.recipe.thymeleaf.models.Recipe;
import com.recipe.thymeleaf.models.User;
import com.recipe.thymeleaf.recipeservices.RecipeRepository;
import com.recipe.thymeleaf.userservices.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientServiceAdapterTest {

    @Autowired
    private IngredientServiceAdapter ingredientServiceAdapter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    User user;
    Recipe recipe;
    IngredientRequest ingredient;

    @Before
    public void init() {
        user = new User("John", "Doe", "monkey@2.com", "password");
        recipe = new Recipe("title", "12 minutes", "instructions", "description", user);
        ingredient = new IngredientRequest("Butter");
    }

    @Test
    public void TestCreateIngredientAdapter() {
        userRepository.save(user);
        recipeRepository.save(recipe);
        Ingredient check = ingredientServiceAdapter.create(ingredient, recipe);
        assertEquals(ingredient.getName(), "Butter");
        assertTrue(check.getRecipe().getCooktime() == "12 minutes");
    }

    @Test
    public void TestDeleteIngredientAdapter() {
        userRepository.save(user);
        recipeRepository.save(recipe);
        Ingredient check = ingredientServiceAdapter.create(ingredient, recipe);
        assertNotNull(user);
        assertNotNull(recipe);
        assertNotNull(ingredient);
        ingredientServiceAdapter.delete(check);
        assertTrue(recipe.getIngredients().isEmpty());
    }

}