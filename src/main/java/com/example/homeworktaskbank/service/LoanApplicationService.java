package com.example.homeworktaskbank.service;

import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.LoanApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoanApplicationService {

    Page<LoanApplication> getAllLoanApplications(Pageable pageable);

    Optional<LoanApplication> getLoanWithId(Long Id);

    LoanApplication addLoanApplication(LoanApplication loanApp);

    LoanApplication updateLoanApplication(Long id, LoanApplication updatedLoanApp);

    void deleteLoanApplication(Long id);

    void deleteLoanApplications();

    Long countAllClientLoans(Client client);

}
