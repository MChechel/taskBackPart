package com.example.homeworktaskbank.dto;

import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@AllArgsConstructor
public class ClientListDto {

        private List<Client> clientList;
        private int pageNum;
        private long totalItems;


}
