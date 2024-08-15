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
import java.util.Collection;
import java.util.List;

@Component
public class PostDaoImpl implements PostDao{
    private static final Logger logger = LoggerFactory.getLogger(PostDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Post> findPostsByUser(User user) {
        String userId = user.getId();
        String sql = "SELECT p.id, p.author_id, p.description, p.photo_url, p.creation_date FROM cat_post AS p LEFT JOIN cat_user AS u ON p.author_id = u.id WHERE u.id = ? ORDER BY p.creation_date DESC;";

        return jdbcTemplate.query(sql, ((rs, rowNum) -> (Post) makePost(rs, user)), userId);
    }

    private List<Post> makePost(ResultSet rs, User user) {

    }

    private static class RowMapperImpl implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            Post post = new Post(resultSet.getInt("id"),
                    resultSet.getObject("author_id", User.class),
                    resultSet.getString("description"),
                    resultSet.getString("photo_url"),
                    resultSet.getDate("creation_date").toLocalDate());
            return null;
        }
    }
}
