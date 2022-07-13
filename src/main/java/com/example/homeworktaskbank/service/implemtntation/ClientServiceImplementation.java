package com.example.homeworktaskbank.service.implemtntation;

import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.repository.ClientRepository;
import com.example.homeworktaskbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImplementation implements ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<Client> getAllClientsByPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> getClientWithId(Long Id) {
        return clientRepository.findById(Id);
    }

    @Override
    public Optional<Client> getClientWithPersonalCode(String code) {
        return clientRepository.findByPersonalCode(code);
    }

    @Override
    public Client addClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        Optional<Client> foundClient = (clientRepository.findById(id));
        if(foundClient.isPresent()){
            Client client = foundClient.get();
            //client.setId(foundClient.get().getId());
            client.setPersonalCode(updatedClient.getPersonalCode());
            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setEmail(updatedClient.getEmail());
            client.setAddress(updatedClient.getAddress());
            client.setPhoneNumber(updatedClient.getPhoneNumber());
//            client.setLoanApplicationList(updatedClient.getLoanApplicationList());
            return clientRepository.save(client);
        }else{
            return null;
        }
    }

    @Override
    public void deleteClientById(Long id) {
        Optional<Client> foundClient = (clientRepository.findById(id));
        if(foundClient.isPresent()){
            clientRepository.deleteById(foundClient.get().getId());
        }else{

        }
    }

    @Override
    public void deleteClients() {
        clientRepository.deleteAll();
    }


}
