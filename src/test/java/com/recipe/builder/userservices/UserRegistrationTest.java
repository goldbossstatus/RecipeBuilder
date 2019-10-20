package com.recipe.builder.userservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@EnableJpaRepositories
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRegistrationTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registrationTest() {

        this.setUp();
        MvcResult mvcResult = null;
        try {

            int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
            String email = String.format("gygac-test-%d@example.com", randomNum);

            mvcResult = mvc.perform(
                    post("/registration")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .param("firstName", "Madaline")
                            .param("lastName", "Newman")
                            .param("email", email)
                            .param("confirmEmail", email)
                            .param("password", "Pa$$w0rd!")
                            .param("confirmPassword", "Pa$$w0rd!")
            )
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int status;
        String content = null;
        status = mvcResult.getResponse().getStatus();
        assertEquals(302, status);
        try {
            content = mvcResult.getResponse().getContentAsString();
            // @todo Redirect is ok, how to test for the displayed content?
            // The line below is empty
            //assertTrue(content.lastIndexOf("You've successfully registered") > -1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
