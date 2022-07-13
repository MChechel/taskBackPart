package com.example.homeworktaskbank.service.implemtntation;

import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.LoanApplication;
import com.example.homeworktaskbank.repository.LoanApplicationRepository;
import com.example.homeworktaskbank.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class LoanApplicationServiceImplementation implements LoanApplicationService {

    private LoanApplicationRepository loanAppRepository;

    @Autowired
    public LoanApplicationServiceImplementation(LoanApplicationRepository loanAppRepository) {
        this.loanAppRepository = loanAppRepository;
    }


    @Override
    public Page<LoanApplication> getAllLoanApplications(Pageable pageable) {
        return loanAppRepository.findAll(pageable);
    }

    @Override
    public Optional<LoanApplication> getLoanWithId(Long Id) {
        return loanAppRepository.findById(Id);
    }


    @Override
    public LoanApplication addLoanApplication(LoanApplication loanApp) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        loanApp.setLastUpdateTime(dateFormat.format(date)+"- registration time");
        loanAppRepository.save(loanApp);
        return loanApp;
    }

    @Override
    public LoanApplication updateLoanApplication(Long id, LoanApplication updatedLoanApp) {
        Optional<LoanApplication> foundLoan = (loanAppRepository.findById(id));
        if(foundLoan.isPresent()){
            LoanApplication loan = foundLoan.get();
            loan.setLoanSum(updatedLoanApp.getLoanSum());
            loan.setLoanType(updatedLoanApp.getLoanType());
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            loan.setLastUpdateTime(dateFormat.format(date)+"- last update time");
            return loanAppRepository.save(loan);
        }else{
            return null;
        }
    }

    @Override
    public void deleteLoanApplication(Long id) {
        Optional<LoanApplication> foundLoan = (loanAppRepository.findById(id));
        if(foundLoan.isPresent()){
            loanAppRepository.deleteById(foundLoan.get().getId());
        }else{

        }
    }

    @Override
    public void deleteLoanApplications() {
        this.loanAppRepository.deleteAll();
    }

    Long count;
    @Override
    public Long countAllClientLoans(Client client) {
        count = 0l;
        Iterable<LoanApplication> applicationList = this.loanAppRepository.findAll();
        applicationList.forEach((x)-> {
            if(x.getClient().equals(client)){
                this.count++;
            }

        });

        return count;
    }

}
