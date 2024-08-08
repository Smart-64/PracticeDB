package ru.practice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class HackCatService {
    private final Logger logger = LoggerFactory.getLogger(HackCatService.class);

    public static final String JDBC_URL = "jdbc:postgresql://127.0.0.1:5432/cats";
    public static final String JDBC_USERNAME = "kitty";
    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    private void tryPassword(String jdbcPassword) throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(jdbcPassword);
        dataSource.setDriverClassName(JDBC_DRIVER);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("SELECT 1;");
        logger.info("Выполнена команда SELECT 1;");
    }

    public void doHack() throws Exception {
        List<String> catWordList = Arrays.asList("meow", "purr", "pur", "zzz", "purrrrr");
        Iterator<String> iterator = catWordList.iterator();
        while (iterator.hasNext()) {
            try {
                String currentWord = iterator.next();
                tryPassword(currentWord);
                logger.info("Блок метода tryPassword пройден");
                return;
            } catch (Exception exception) {
                logger.error("Ошибка подключения к БД. Попробуйте другой пароль. {}", exception.getMessage());
            }
        }
    }
}
