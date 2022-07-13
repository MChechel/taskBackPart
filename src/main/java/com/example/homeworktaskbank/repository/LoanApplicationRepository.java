package com.example.homeworktaskbank.repository;

import com.example.homeworktaskbank.dto.ClientListDto;
import com.example.homeworktaskbank.model.Client;
import com.example.homeworktaskbank.model.LoanApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LoanApplicationRepository extends PagingAndSortingRepository<LoanApplication, Long> {


}
