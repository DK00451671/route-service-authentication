package com.route.authentication.routeserviceauthentication.controller;


import com.route.authentication.routeserviceauthentication.dto.UserCredentialDTO;
import com.route.authentication.routeserviceauthentication.dto.UserDTO;
import com.route.authentication.routeserviceauthentication.service.UserCredentialValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("oc-api/auth-service")
public class AuthenticationController {

    @Autowired
    private UserCredentialValidationService userCredentialValidationService;

    private String token;

    final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public String authenticaitonUser(@RequestBody UserCredentialDTO
                                                 userCredentialDTO) {


        String password = userCredentialDTO.getPassword();
        String username = userCredentialDTO.getUsername();

        UserDTO userDTO  =  userCredentialValidationService.validateUser(username , password);


        Optional<UserDTO> sc = Optional.ofNullable(userDTO);

        if(sc.isPresent()) {
            token = userCredentialValidationService.createToken(userDTO);
        }
        else
            return "";


          return token;
    }

}
