package ru.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    // TODO: Task-1
    // Переименовать package
    // Починить старт приложения (org.postgresql.util.PSQLException: Connection to localhost:5432 refused)
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}