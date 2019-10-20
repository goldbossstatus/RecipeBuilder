package com.recipe.builder.recipeservices;


import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.User;
import com.recipe.builder.userservices.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@EnableAutoConfiguration
@EnableJpaRepositories
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateRecipeTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    public CreateRecipeTest(RecipeServiceAdapter rsa, RecipeRepository rr) {
//        this.recipeServiceAdapter = rsa;
//        this.recipeRepository = rr;
//    }

    User user;
    Recipe recipe;

    @Before
    public void init() {
        int randomNum = getRandomNum();
        String email = String.format("user-%d@example.com", randomNum);
        user = new User("John", "Doe", email, "password");recipe = new Recipe("Breakfast Buurrito", "1 hour", "Here are the instructions",
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
