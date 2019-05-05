package com.app.model.dao;

import com.app.model.Account;
import com.app.model.dao.generic.GenericDao;

import java.util.Optional;

public interface AccountDao extends GenericDao<Account> {
    Optional<Long> getIdByLoginAndPassword(String login, String password);
    Optional<Long> getIdByLogin(String login);
    String getPasswordById(Long id);
    boolean isLoginBusy(String login);
}