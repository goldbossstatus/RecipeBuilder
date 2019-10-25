package com.recipe.builder.userservices;

import com.recipe.builder.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceAdapterTest {

    @Autowired
    private UserServiceAdapter userServiceAdapter;

    String email1 = "user1@examples.com";
    String email2 = "user2@examples.com";


    UserRegistrationDto user1 = new UserRegistrationDto("John", "Doe", "password", "password", email1, email1);
    UserRegistrationDto user2 = new UserRegistrationDto("Jane", "Doe", "password", "password", email2, email2);

    @Test
    public void TestSaveAdapter() {
        userServiceAdapter.save(user1);
        User savedUser = userServiceAdapter.findByEmail(email1);
        assertEquals(email1, savedUser.getEmail());
    }

}
