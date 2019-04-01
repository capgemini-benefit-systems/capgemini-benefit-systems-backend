package com.app.model.dao;

import com.app.model.Account;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends AbstractGenericDao<Account> implements AccountDao  {
}