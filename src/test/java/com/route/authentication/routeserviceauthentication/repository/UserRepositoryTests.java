package com.route.authentication.routeserviceauthentication.repository;

import com.route.authentication.routeserviceauthentication.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public interface UserRepositoryTests extends JpaRepository<User,Integer> {

    @Test
    @Transactional
    public User findUserByUsernameAndPassword(String username, String password);

}
