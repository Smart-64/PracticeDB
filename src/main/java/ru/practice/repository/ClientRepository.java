package ru.practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practice.util.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
