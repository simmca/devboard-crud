package com.project.management.devboard.dao;

import com.project.management.devboard.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int userId);

    User getUserByUsername(String username);

    User createUser(User newUser);
}