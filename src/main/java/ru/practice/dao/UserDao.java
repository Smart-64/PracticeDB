package ru.practice.dao;

import ru.practice.model.User;

import java.util.List;

public interface UserDao {

    List<User> findUserById(String id);
}
