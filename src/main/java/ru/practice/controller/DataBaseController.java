package ru.practice.controller;

import ru.practice.Utils.Client;
import ru.practice.service.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataBaseController {
    ClientServiceImpl clientService;



    @GetMapping("/client")
    public String showTable(@RequestParam int id) {
        List<Client> result = clientService.readAll();

        return result.get(id - 1).getName();
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client) {
        clientService.create(client);
    }
}
