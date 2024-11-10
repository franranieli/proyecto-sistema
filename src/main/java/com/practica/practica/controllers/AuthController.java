package com.practica.practica.controllers;

import com.practica.practica.dao.UserDao;
import com.practica.practica.models.User;
import com.practica.practica.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLogged = userDao.verifyUserLogin(user);

        if (userLogged != null) {

            String tokenJwt = jwtUtil.create(String.valueOf(userLogged.getId()), userLogged.getEmail());

            return tokenJwt;

        }else return "ERROR";

    }
}
