package com.app.controller;


import com.app.model.Transaction;
import com.app.model.dao.TransactionDao;
import com.app.model.dto.ProjectDto;
import com.app.model.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionDao transactionDao;

    @Autowired
    public TransactionController(TransactionDao transactionDao){
        this.transactionDao=transactionDao;
    }

    @GetMapping("/all")
    public List<TransactionDto> findAll(){

        List <Transaction> transactions=transactionDao.findAll();
        return transactions
                .stream()
                .map(TransactionDto::getTransactionDtoByTransaction)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public TransactionDto addTransaction(TransactionDto transactionDto){
        Transaction transaction= TransactionDto.getTransactionByTransactionDto(transactionDto);
        return TransactionDto.getTransactionDtoByTransaction(transactionDao.insert(transaction));
    }


}
