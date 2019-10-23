package com.recipe.builder.tagservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.Tag;
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

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateTagTest {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;

    User user;
    Recipe recipe1;
    Tag tag1;
    Tag tag2;

    @Before
    public void init() {
        int randomNum = getRandomNum();
        String email = String.format("user-%d@example.com", randomNum);
        user = new User("John", "Doe", email, "password");
        recipe1 = new Recipe("Breakfast Burrito", "1 hour", "Here are the instructions",
                "Here is the description", user);
        tag1 = new Tag("Mexican", recipe1);
        tag2 = new Tag("Breakfast", recipe1);
    }

    private int getRandomNum() {
        return ThreadLocalRandom.current().nextInt(0, 1000);
    }

    @Test
    public void TestSaveNewTag() {
        userRepository.save(user);
        recipeRepository.save(recipe1);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        assertEquals(tag1.getRecipe(), recipe1);
        assertEquals(tag2.getRecipe(), recipe1);
        assertNotEquals(recipe1.getIngredients(), tag2);
        assertEquals(tagRepository.findByRecipeId(recipe1.getId()).size(), 2);
    }

    @Test
    public void TestTagDetails() {
        assertEquals(tag1.getTitle(), "Mexican");
        assertEquals(tag2.getTitle(), "Breakfast");
        assertNotEquals(tag1.getTitle(), "monkey");
        assertNotEquals(tag2.getTitle(), "monkey");
    }

}
