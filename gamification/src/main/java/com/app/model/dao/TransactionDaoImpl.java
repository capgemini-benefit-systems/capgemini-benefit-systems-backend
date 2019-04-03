package com.app.model.dao;

import com.app.model.Transaction;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl extends AbstractGenericDao<Transaction> implements TransactionDao  {
}