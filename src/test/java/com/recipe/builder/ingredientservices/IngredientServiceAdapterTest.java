package com.recipe.builder.ingredientservices;

import com.recipe.builder.models.Ingredient;
import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import com.recipe.builder.recipeservices.RecipeRepository;
import com.recipe.builder.userservices.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void TestIngredientAdapter() {
        userRepository.save(user);
        recipeRepository.save(recipe);
        Ingredient check = ingredientServiceAdapter.create(ingredient, recipe);
        assertEquals(ingredient.getName(), "Butter");
        assertTrue(check.getRecipe().getCooktime() == "12 minutes");
    }
}
