package ru.practice.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
            logger.info("Пользователь с id = '{}' найден, nickname {} ",
                        userRows.getString("id"), userRows.getString("nickname"));
            User user = new User();
            user.setId(userRows.getString("id"));
            user.setUsername(userRows.getString("username"));
            user.setNickname(userRows.getString("nickname"));
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
