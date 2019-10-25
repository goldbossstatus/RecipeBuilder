package com.recipe.builder.recipeservices;


import com.recipe.builder.models.Ingredient;
import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import com.recipe.builder.userservices.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

//    public CreateRecipeTest(RecipeRepository rr, UserRepository ur) {
//        this.recipeRepository = rr;
//        this.userRepository = ur;
//    }

    User user;
    Recipe recipe;

    @Before
    public void init() {
        int randomNum = getRandomNum();
        String email = String.format("user-%d@example.com", randomNum);
        user = new User("John", "Doe", email, "password");
        recipe = new Recipe("Breakfast Buurrito", "1 hour", "Here are the instructions",
                "Here is the description", user);
    }

    private int getRandomNum() {
        return ThreadLocalRandom.current().nextInt(0, 1000);
    }

    @Test
    public void TestSaveNewRecipe() {
        userRepository.save(user);
        recipeRepository.save(recipe);
        assertNotNull(recipe);
        assertEquals(user.getId(), recipe.getUser().getId());
        assertEquals(recipeRepository.findByUserId(user.getId()).size(), 1);
    }

}
