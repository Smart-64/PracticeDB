package ru.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.Impl.PostDaoImpl;
import ru.practice.Impl.UserDaoImpl;
import ru.practice.model.Post;
import ru.practice.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDaoImpl userDao;
    private final PostDaoImpl postDao;

    @Autowired
    public UserService(UserDaoImpl userDao, PostDaoImpl postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public Optional<User> findUserById(String id) {
        return userDao.findUserById(id);
    }
}
