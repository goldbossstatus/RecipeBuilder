package com.recipe.thymeleaf.recipeservices;

import com.recipe.thymeleaf.models.Recipe;
import com.recipe.thymeleaf.models.User;
import com.recipe.thymeleaf.userservices.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipeServiceAdapterTest {

    @Autowired
    private RecipeServiceAdapter recipeServiceAdapter;

    @Autowired
    private UserRepository userRepository;

    User user;
    RecipeRequest recipe;

    @Before
    public void init() {
        user = new User("John", "Doe", "monkey@2.com", "password");
        recipe = new RecipeRequest("title", "12 minutes", "instructions", "description");
    }

    @Test
    public void TestRecipeAdapter() {
        userRepository.save(user);
        Recipe recipeCheck = recipeServiceAdapter.save(recipe, user);
        ArrayList<Recipe> recipes = recipeServiceAdapter.findByUserId(user.getId());
        boolean check = recipes.isEmpty() == false;
        assertTrue(check);
        assertEquals(recipe.getTitle(), recipeCheck.getTitle());
    }


}
