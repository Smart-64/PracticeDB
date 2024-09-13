package ru.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.exception.UserNotFoundException;
import ru.practice.model.Post;
import ru.practice.service.PostService;

import java.util.Collection;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
    Пример запроса: http://localhost:8080/posts?userId=id
    */
    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam("userId") String userId) throws UserNotFoundException {
        return postService.findPostsByUserId(userId);
    }
}
