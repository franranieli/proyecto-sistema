package com.practica.practica.controllers;

import com.practica.practica.dao.UserDao;
import com.practica.practica.models.User;
import com.practica.practica.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;


    /*
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Frank");
        user.setLastName("Jhonson");
        user.setEmail("frank@gmail.com");
        user.setPhone("+341178326655");
        user.setPassword("pass1");
        return user;
    }*/

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {

        if (!validateToken(token)) {
            return null;
        }


        return userDao.getUsers();

    }

    private boolean validateToken(String token) {
        String userID = jwtUtil.getKey(token);
        return userID != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.register(user);

    }

    @RequestMapping(value = "api/usuario1")
    public User editUser() {
        User user = new User();
        user.setName("Frank");
        user.setLastName("Jhonson");
        user.setEmail("frank@gmail.com");
        user.setPhone("+341178326655");
        user.setPassword("pass1");
        return user;
    }


    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {

        if (!validateToken(token)) {
            return;
        }
        userDao.delete(id);

    }

    /*
    @RequestMapping(value = "api/usuario3")
    public User searchUser() {
        User user = new User();
        user.setName("Frank");
        user.setLastName("Jhonson");
        user.setEmail("frank@gmail.com");
        user.setPhone("+341178326655");
        user.setPassword("pass1");
        return user;
    }*/

}
