package com.example.homeworktaskbank.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( indexes = {
    @Index(name = "fullName", columnList = "firstName, lastName")
}
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
//    @OneToMany
//    private List<LoanApplication> loanApplicationList;


}
