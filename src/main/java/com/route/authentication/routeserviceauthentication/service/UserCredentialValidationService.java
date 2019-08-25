package com.route.authentication.routeserviceauthentication.service;

import com.route.authentication.routeserviceauthentication.dto.UserDTO;
import com.route.authentication.routeserviceauthentication.entity.User;
import com.route.authentication.routeserviceauthentication.mapper.UserMapper;
import com.route.authentication.routeserviceauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialValidationService {

   @Autowired
    private UserRepository userRepository;

   @Autowired
   private JWTActionService jwtActionService;

    @Autowired
    UserMapper userMapper;

    public UserDTO validateUser(String username, String password) {

        UserDTO test = null;
        User objUser = userRepository.findUserByUsernameAndPassword(username,password);
        test = userMapper.userToUserDTO(objUser);
        return test;

    }

    public String createToken(UserDTO userDTO) {

        String token =  jwtActionService.createToken(userDTO.getUsername(),userDTO.getGender());
        return token;
    }
}
