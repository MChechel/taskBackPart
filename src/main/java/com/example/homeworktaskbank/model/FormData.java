package com.example.homeworktaskbank.model;

import lombok.Data;

@Data
public class FormData {
    /*
    *
    *
          this.form.setValue({
          id:null,
          firstName:null,
          lastName:null,
          personalCode:null,
          loanType:null,
          loanSum:null,
          email:null,
          phoneNumber:null,
          address:null,
         })
    * */

    private long id;
    private String firstName;
    private String lastName;
    private String personalCode;
    private String loanType;
    private String email;
    private String phoneNumber;
    private Double loanSum;
    private String address;

}
