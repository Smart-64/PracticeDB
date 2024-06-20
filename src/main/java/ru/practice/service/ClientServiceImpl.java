package ru.practice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.repository.ClientRepository;
import ru.practice.util.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Client client) {
        repository.save(client);
        logger.info("Create new client\n id: {}\n name: {}\n email: {}\n phone: {}",
                client.getId(), client.getName(), client.getEmail(), client.getPhone());
    }

    @Override
    public List<Client> readAll() {
        return repository.findAll();
    }

    @Override
    public Client read(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Client client, int id) {
        if (repository.existsById(id)) {
            client.setId(id);
            repository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
