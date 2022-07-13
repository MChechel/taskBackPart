package com.example.homeworktaskbank.dto;

import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class LoanApplicationDTO {

    private List<LoanApplication> loansList;
    private int pageNum;
    private long totalItems;

}
