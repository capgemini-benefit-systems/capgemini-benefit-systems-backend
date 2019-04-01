package com.app.model.dao;

import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl  extends AbstractGenericDao<User> implements UserDao  {
}