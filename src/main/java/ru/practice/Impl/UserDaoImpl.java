package ru.practice.Impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.practice.dao.UserDao;
import ru.practice.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findUserById(String id) {
        String query = "SELECT * FROM cat_user WHERE id = '" + id + "';";
        return jdbcTemplate.query(query, new UserRowMapper());
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setNickname(rs.getString("username"));
            user.setNickname(rs.getString("nickname"));
            return user;
        }
    }
}
