package com.example.homeworktaskbank.controller;

import com.example.homeworktaskbank.dto.LoanApplicationDTO;
import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.FormData;
import com.example.homeworktaskbank.model.LoanApplication;
import com.example.homeworktaskbank.service.ClientService;
import com.example.homeworktaskbank.service.LoanApplicationService;
import com.example.homeworktaskbank.utils.AppUtils;
import com.example.homeworktaskbank.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/loanApp")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final ClientService clientService;

    private final DatabaseUtil databaseUtil;

    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService, ClientService clientService) {
        this.loanApplicationService = loanApplicationService;
        this.clientService = clientService;
        this.databaseUtil = new DatabaseUtil();
    }


    @GetMapping
    public ResponseEntity<LoanApplicationDTO> getQuestionsByPage(
            @RequestParam(name="page", defaultValue = "0") int pageNum,
            @RequestParam(name = "items", defaultValue = AppUtils.DEFAULT_ITEMS_PER_PAGE) int totItems,
            @RequestParam(name = "sort", defaultValue = "lastUpdateTime") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order
    ){
        Page<LoanApplication> loanPage = loanApplicationService.getAllLoanApplications(PageRequest.of(pageNum, totItems, AppUtils.getSortOfColumn(sort, order)));
        List<LoanApplication> loanApp = new ArrayList<>();
        loanPage.forEach(loanApp::add);

        LoanApplicationDTO loanListDto = new LoanApplicationDTO(loanApp, pageNum, loanPage.getTotalElements());
        return ResponseEntity.ok(loanListDto);
    }


    @GetMapping("client")
    public ResponseEntity<Long> getApplicationCount( @RequestParam(name="clientId", defaultValue = "0") long clientId){
        try{
        Long count = loanApplicationService.countAllClientLoans(clientService.getClientWithId(clientId).get());
            return new ResponseEntity<>(count,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getQuestionWithId(@PathVariable("id") Long id){
        Optional<LoanApplication> loanApplication = loanApplicationService.getLoanWithId(id);
        try{
            return new ResponseEntity<>(loanApplication.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/save")
    public ResponseEntity<LoanApplication> saveDumpFile(
            @RequestParam(name = "fileNameAndPath", defaultValue = "dump.sql") String fileNameAndPath)
            throws IOException, InterruptedException {
        databaseUtil.backup("root","root","homework",fileNameAndPath);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody FormData data){
        LoanApplication loan = new LoanApplication();
        loan.setLoanSum(data.getLoanSum());
        loan.setLoanType(data.getLoanType());
        Optional<Client> client = clientService.getClientWithPersonalCode(data.getPersonalCode());
        if(client.isPresent()){
            loan.setClient(client.get());
        }else{
            Client newClient = new Client();
            newClient.setAddress(data.getAddress());
            newClient.setEmail(data.getEmail());
            newClient.setFirstName(data.getFirstName());
            newClient.setLastName(data.getLastName());
            newClient.setPersonalCode(data.getPersonalCode());
            newClient.setPhoneNumber(data.getPhoneNumber());
            clientService.addClient(newClient);
            loan.setClient(newClient);
        }
        try{
            return new ResponseEntity<LoanApplication>(loanApplicationService.addLoanApplication(loan), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateQuestion(@RequestBody FormData formData){
        LoanApplication loan = new LoanApplication();
        loan.setLoanSum(formData.getLoanSum());
        loan.setLoanType(formData.getLoanType());
        Optional<Client> client = clientService.getClientWithPersonalCode(formData.getPersonalCode());
        client.get().setAddress(formData.getAddress());
        client.get().setEmail(formData.getEmail());
        client.get().setFirstName(formData.getFirstName());
        client.get().setLastName(formData.getLastName());
        client.get().setPhoneNumber(formData.getPhoneNumber());

        if(client.isPresent()){
            loan.setClient(client.get());
        }
        if(loanApplicationService.updateLoanApplication(formData.getId(), loan)!=null){

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Client> updateQuestion(@PathVariable Long id,
//                                                 @RequestBody LoanApplication loan,
//                                                 @RequestParam(name="clientId", defaultValue = "1") Long clientId){
//        Optional<Client> client = clientService.getClientWithId(clientId);
//        if(client.isPresent()){
//            loan.setClient(client.get());
//        }
//
//        if(loanApplicationService.updateLoanApplication(id, loan)!=null){
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLoanApplicationById(@PathVariable("id") Long id){
        try{
            LoanApplication loan = loanApplicationService.getLoanWithId(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
            loanApplicationService.deleteLoanApplication(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteLoanApplications(){
        try{
            loanApplicationService.deleteLoanApplications();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
