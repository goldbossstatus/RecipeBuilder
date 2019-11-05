package com.recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.controller.UserRegistrationController;
import com.recipe.userservices.IUserService;
import com.recipe.userservices.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRegistrationController.class)
public class TestUserAPIController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userServiceAdapter;

    @Test
    public void TestCreateUser() {

        MvcResult mvcResult = null;

        try {

            UserDTO request = new UserDTO();
            request.setFistName("Ethan");
            request.setLastName("Haga");
            request.setPassword("password");
            request.setEmail("my@email.com");
            ObjectMapper mapper = new ObjectMapper();
            String requestString = mapper.writeValueAsString(request);

            UserDTO response = new UserDTO(1L, "Ethan", "Haga", "my@email.com", null);

            Mockito.when(userServiceAdapter.saveAPI(request)).thenReturn(response);

            mvc.perform(
                    post("/api/registration")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(requestString))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
