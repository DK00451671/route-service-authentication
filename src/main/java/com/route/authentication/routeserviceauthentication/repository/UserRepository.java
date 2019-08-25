package com.route.authentication.routeserviceauthentication.repository;

import com.route.authentication.routeserviceauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Transactional
    public User findUserByUsernameAndPassword(String username, String password);

}
