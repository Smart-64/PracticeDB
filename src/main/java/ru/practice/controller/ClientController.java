package ru.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.practice.service.ClientServiceImpl;
import ru.practice.util.Client;

import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        clientService.create(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable(value = "id") Integer id, @RequestBody Client client) {
        return clientService.update(client, id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable(value = "id") Integer id) {
        return clientService.delete(id);
    }

}
