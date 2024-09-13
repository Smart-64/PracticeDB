package ru.practice.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.practice.dao.PostDao;
import ru.practice.model.Post;
import ru.practice.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class PostDaoImpl implements PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Post> findPostsByUser(User user) {
        String sql = "SELECT p.id, p.author_id, p.description, p.photo_url, p.creation_date " +
                "FROM cat_post AS p LEFT JOIN cat_user AS u ON p.author_id = u.id " +
                "WHERE u.id = ? ORDER BY p.creation_date DESC;";
        logger.info("SQL запрос: {}", sql);
        logger.info("Отправка запрос в БД");
        return jdbcTemplate.query(sql, ((rs, rowNum) -> makePost(rs, user)), user.getId());
    }

    private Post makePost(ResultSet rs, User user) throws SQLException {
        Post post = new Post(
                rs.getInt("id"),
                user,
                rs.getString("description"),
                rs.getString("photo_url"),
                rs.getDate("creation_date").toLocalDate()
        );
        logger.info("Получен объект Post с полями: id = {}, user = {}, description = {}, photo_url = {}, creation_date = {}",
                post.getId(), post.getAuthor_id().getNickname(), post.getDescription(), post.getPhoto_url(), post.getCreation_date());
        return post;
    }
}
