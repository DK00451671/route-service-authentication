package com.route.authentication.routeserviceauthentication.mapper;

import com.route.authentication.routeserviceauthentication.dto.UserDTO;
import com.route.authentication.routeserviceauthentication.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

     UserDTO userToUserDTO(User entity);
}
