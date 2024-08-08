package ru.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.Impl.UserDaoImpl;
import ru.practice.model.User;
import ru.practice.service.HackCatService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class SimpleController {


    private final HackCatService hackCatService;
    private final UserDaoImpl userDao = new UserDaoImpl();

    @Autowired
    public SimpleController(HackCatService hackCatService) {
        this.hackCatService = hackCatService;
    }

    @GetMapping("/do-hack")
    public void doHack() throws Exception {
        hackCatService.doHack();
    }

    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }

    @GetMapping("/user/{id}")
    public List<User> getUserById(@PathVariable (value = "id") String id) {

    }
}
