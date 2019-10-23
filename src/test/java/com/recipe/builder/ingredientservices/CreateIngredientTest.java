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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateIngredientTest {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

//    public CreateIngredientTest(IngredientRepository ingredientRepository,
//                                RecipeRepository recipeRepository,
//                                UserRepository userRepository) {
//        this.ingredientRepository = ingredientRepository;
//        this.recipeRepository = recipeRepository;
//        this.userRepository = userRepository;
//    }

    User user;
    Recipe recipe1;
    Ingredient one;
    Ingredient two;

    @Before
    public void init() {
        int randomNum = getRandomNum();
        String email = String.format("user-%d@example.com", randomNum);
        user = new User("John", "Doe", email, "password");
        recipe1 = new Recipe("Breakfast Burrito", "1 hour", "Here are the instructions",
                "Here is the description", user);
        one = new Ingredient(user, "Bacon", recipe1);
        two = new Ingredient(user, "Cheese", recipe1);
    }

    private int getRandomNum() {
        return ThreadLocalRandom.current().nextInt(0, 1000);
    }

//    @Test
//    public void TestSaveNewIngredient() {
//        userRepository.save(user);
//        Recipe recipe = recipeRepository.save(recipe1);
//        ingredientRepository.save(one);
//        ingredientRepository.save(two);
//        recipe.getIngredients().add(one);
//        recipe.getIngredients().add(two);
//        recipeRepository.save(recipe);
//        List<Ingredient> myList = recipe.getIngredients();
//        assertEquals(one.getRecipe(), recipe);
//        assertNotEquals(recipe.getIngredients(), two);
//        assertEquals(myList.size(), 2);
//        assertEquals(ingredientRepository.findByRecipeId(recipe.getId()).size(), 2);
//    }

    @Test
    public void TestSaveNewIngredient() {
        userRepository.save(user);
        recipeRepository.save(recipe1);
        ingredientRepository.save(one);
        ingredientRepository.save(two);
        assertEquals(one.getRecipe(), recipe1);
        assertEquals(two.getRecipe(), recipe1);
        assertNotEquals(recipe1.getIngredients(), two);
        assertEquals(ingredientRepository.findByRecipeId(recipe1.getId()).size(), 2);
    }

    @Test
    public void TestIngredientDetails() {
        assertEquals(one.getName(), "Bacon");
        assertEquals(two.getName(), "Cheese");
        assertNotEquals(one.getName(), "monkey");
        assertNotEquals(two.getName(), "monkey");
    }

}
