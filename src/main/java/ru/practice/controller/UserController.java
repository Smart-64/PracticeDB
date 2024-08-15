package ru.practice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.model.Post;
import ru.practice.model.User;
import ru.practice.service.UserService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public Optional<User> getUserById(@PathVariable(value = "login") String login) {
        return userService.findUserById(login);
    }

    @GetMapping("/posts/{userId}")
    public Collection<Post> findPostsByUser(@PathVariable(value = "userId") String userId) {
        Optional<User> user = getUserById(userId);
        return userService.findPostsByUser(user.orElse(null));
    }


}
