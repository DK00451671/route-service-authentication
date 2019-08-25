package com.route.authentication.routeserviceauthentication.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.route.authentication.routeserviceauthentication.dto.UserCredentialDTO;
import com.route.authentication.routeserviceauthentication.entity.User;
import com.route.authentication.routeserviceauthentication.repository.UserRepository;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import com.route.authentication.routeserviceauthentication.repository.UserRepositoryTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.swing.text.AbstractDocument;
import java.nio.charset.Charset;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@DataJpaTest

public class AuthenticationControllerTests {

   final static Logger logger = LoggerFactory.getLogger(AuthenticationControllerTests.class);

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    public static final MediaType APPLICATION_TYPE = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void authenticaitonUserTest() throws Exception {


       String s1= new String("dinesh");


        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();
        User user = new User();
        //set request data
        userCredentialDTO.setUsername("dinesh1");
        userCredentialDTO.setPassword("Aa123456");
        //set user
        user.setUsername("dinesh");
        user.setPassword("Aa123456");
        user.setAge(30);
        user.setGender('M');
        user.setName("test");
        userRepository.save(user);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        byte jsonRequest[] = objectMapper.writeValueAsBytes(userCredentialDTO);

        logger.info("JSONNNNNNNNNNNNN="+jsonRequest);
        MvcResult result = mvc.perform(post("/oc-api/auth-service/login")
                            .contentType(APPLICATION_TYPE)
                            .content( jsonRequest))
                .andExpect(status().isOk())
                 //.andDo(print());
                .andReturn();

      //  logger.debug("RESULTTTTTTTTTTTTTTTTT="+result.getResponse().getContentAsString());
    }

}
