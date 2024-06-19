package ru.practice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.practice.util.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final static Map<Integer, Client> CLIENTS_MAP = new HashMap<>();

    private final static AtomicInteger CLIENT_ID = new AtomicInteger();

    @Override
    public void create(Client client) {
        Integer id = CLIENT_ID.incrementAndGet();
        client.setId(id);
        CLIENTS_MAP.put(id, client);
        logger.info("Create new client\n id: {}\n name: {}\n email: {}\n phone: {}",
                client.getId(), client.getName(), client.getEmail(), client.getPhone());
    }

    @Override
    public List<Client> readAll() {
        return CLIENTS_MAP.values().stream().collect(Collectors.toList());
    }

    @Override
    public Client read(int id) {
        return CLIENTS_MAP.getOrDefault(id, null);
    }

    @Override
    public boolean update(Client client, int id) {
        if (CLIENTS_MAP.containsKey(id)) {
            client.setId(id);
            CLIENTS_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (CLIENTS_MAP.containsKey(id)) {
            CLIENTS_MAP.remove(id);
            return true;
        }
        return false;
    }
}
