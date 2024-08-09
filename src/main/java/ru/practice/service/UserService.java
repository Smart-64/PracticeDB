package ru.practice.service;

import org.springframework.stereotype.Service;
import ru.practice.Impl.UserDaoImpl;
import ru.practice.model.User;

import java.util.Optional;

@Service
public class UserService {
    private final UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findUserById(String id) {
        return userDao.findUserById(id);
    }
}
