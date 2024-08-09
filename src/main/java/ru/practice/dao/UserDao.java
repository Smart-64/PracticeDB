package ru.practice.dao;

import ru.practice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserById(String id);
}
