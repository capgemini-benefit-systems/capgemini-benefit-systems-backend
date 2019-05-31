package com.app.controller;


import com.app.model.Award;
import com.app.model.Transaction;
import com.app.model.User;
import com.app.model.dao.AwardDao;
import com.app.model.dao.TransactionDao;
import com.app.model.dao.UserDao;
import com.app.model.dto.ProjectDto;
import com.app.model.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionDao transactionDao;
    private UserDao userDao;
    private AwardDao awardDao;

    @Autowired
    public TransactionController(TransactionDao transactionDao, UserDao userDao, AwardDao awardDao){
        this.transactionDao=transactionDao;
        this.userDao=userDao;
        this.awardDao=awardDao;
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

    /*@GetMapping("/{userId}/buyAward/{awardId}")
    public void buyAwardByUserAndAwardId(@PathVariable Long userId, @PathVariable Long awardId) throws NullPointerException{
        User user=userDao.findById(userId).orElseThrow(NullPointerException::new);
        Award award=awardDao.findById(awardId).orElseThrow(NullPointerException::new);
        Transaction transaction=transactionDao.getTransactionByUserAndAwardId
        user.setCurrentPoints(user.getCurrentPoints()-);
    }*/


}
