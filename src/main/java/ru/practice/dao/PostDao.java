package ru.practice.dao;

import ru.practice.model.Post;
import ru.practice.model.User;

import java.util.Collection;

public interface PostDao {

    Collection<Post> findPostsByUser(User user);
}
