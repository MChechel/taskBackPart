package com.example.homeworktaskbank.controller;


import com.example.homeworktaskbank.dto.ClientListDto;
import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.service.ClientService;
import com.example.homeworktaskbank.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<ClientListDto> getQuestionsByPage(
            @RequestParam(name="page", defaultValue = "0") int pageNum,
            @RequestParam(name = "items", defaultValue = AppUtils.DEFAULT_ITEMS_PER_PAGE) int totItems,
            @RequestParam(name = "sort", defaultValue = "personalCode") String sort,
            @RequestParam(name = "order", defaultValue = "desc") String order
    ){
        Page<Client> clientPage = clientService.getAllClientsByPage(PageRequest.of(pageNum, totItems, AppUtils.getSortOfColumn(sort, order)));
        List<Client> clients = new ArrayList<>();
        clientPage.forEach(clients::add);

        ClientListDto clientListDtp = new ClientListDto(clients, pageNum, clientPage.getTotalElements());
        return ResponseEntity.ok(clientListDtp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getQuestionWithId(@PathVariable("id") Long id){
        Optional<Client> question = clientService.getClientWithId(id);
        try{
            return new ResponseEntity<Client>(question.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping
    public ResponseEntity<Client> createQuestion(@RequestBody Client newClient){
        try{
            return new ResponseEntity<>(clientService.addClient(newClient), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateQuestion(@PathVariable Long id,@RequestBody Client updatedClient){
        if(clientService.updateClient(id, updatedClient)!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClientnById(@PathVariable("id") Long id){
        try{
            Client client = clientService.getClientWithId(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
            clientService.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteClients(){
        try{
            clientService.deleteClients();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
