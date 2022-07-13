package com.example.homeworktaskbank.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String loanType;
    private double loanSum;
    private String lastUpdateTime;
    @ManyToOne
    private Client client;
}
