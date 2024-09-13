package ru.practice.service;

import org.springframework.stereotype.Service;
import ru.practice.dao.PostDao;
import ru.practice.exception.UserNotFoundException;
import ru.practice.model.Post;
import ru.practice.model.User;

import java.util.Collection;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;

    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findPostsByUserId(String userId) throws UserNotFoundException {
        User user = userService.findUserById(userId)
                .orElseThrow( () -> new UserNotFoundException("Пользователь с userId = " + userId + " не найден."));

        return postDao.findPostsByUser(user);
    }
}
