package com.example.homeworktaskbank.service;

import com.example.homeworktaskbank.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {

    Page<Client> getAllClientsByPage(Pageable pageable);

    Optional<Client> getClientWithId(Long Id);

    Optional<Client> getClientWithPersonalCode(String code);

    Client addClient(Client client);

    Client updateClient(Long id, Client updatedClient);

    void deleteClientById(Long id);

    void deleteClients();

}
