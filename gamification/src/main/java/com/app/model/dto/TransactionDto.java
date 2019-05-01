package com.app.model.dto;

import com.app.model.Transaction;
import com.app.model.dao.AwardDao;
import com.app.model.dao.AwardDaoImpl;
import com.app.model.dao.UserDao;
import com.app.model.dao.UserDaoImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private LocalDate date;
    private Long cost;
    private Long awardId;
    private Long userId;


    public static TransactionDto getTransactionDtoByTransaction(Transaction modelTransaction){
        return TransactionDto.builder()
                .id(modelTransaction.getId())
                .date(modelTransaction.getDate())
                .cost(modelTransaction.getCost())
                .awardId(modelTransaction.getAward() == null ? null : modelTransaction.getAward().getId())
                .userId(modelTransaction.getUser() == null ? null : modelTransaction.getUser().getId())
                .build();
    }

    public static Transaction getTransactionByTransactionDto(TransactionDto transactionDto){
        AwardDao awardDao=new AwardDaoImpl();
        UserDao userDao=new UserDaoImpl();
        return Transaction.builder()
                .id(transactionDto.getId())
                .date(transactionDto.getDate())
                .cost(transactionDto.getCost())
                .award(awardDao.findById(transactionDto.getAwardId()).orElseThrow(NullPointerException::new))
                .user(userDao.findById(transactionDto.getUserId()).orElseThrow(NullPointerException::new))
                .build();
    }

}

