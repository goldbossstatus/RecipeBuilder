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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagServiceAdapterTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TagServiceAdapter tagServiceAdapter;

    User user;
    Recipe recipe;
    TagRequest tag;

    @Before
    public void init() {
        user = new User("John", "Doe", "monkey@2.com", "password");
        recipe = new Recipe("title", "12 minutes", "instructions", "description", user);
        tag = new TagRequest("Breakfast");
    }

    @Test
    public void TestTagServiceAdapter() {
        userRepository.save(user);
        recipeRepository.save(recipe);
        Tag check = tagServiceAdapter.create(tag, recipe);
        assertEquals(check.getTitle(), tag.getTitle());
        assertTrue(check.getRecipe().getTitle() == "title");
    }
}
