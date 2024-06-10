package org.example.Controller;

import org.example.Utils.Client;
import org.example.Utils.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DbController {
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
