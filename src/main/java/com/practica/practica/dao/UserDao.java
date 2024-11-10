package com.practica.practica.dao;

import com.practica.practica.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void delete(Long id);

    void register(User user);

    User verifyUserLogin(User user);
}
