package ru.practice.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.practice.dao.UserDao;
import ru.practice.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findUserById(String id) {
        String sql = "SELECT * FROM cat_user WHERE id = ?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sql, id);
        if (userRows.next()) {
            User user = new User(
                    userRows.getString("id"),
                    userRows.getString("username"),
                    userRows.getString("nickname"));
            logger.info("Пользователь с id = '{}' найден, nickname {} ", user.getId(), user.getNickname());
            return Optional.of(user);
        } else {
            logger.error("Пользователь с id = '{}' не найден. Error.", id);
            return Optional.empty();
        }
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
