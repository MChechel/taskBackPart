package com.example.homeworktaskbank.repository;

import com.example.homeworktaskbank.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Optional<Client> findByPersonalCode(String code);
}
