package com.app.model.dao;

import com.app.model.User;
import com.app.model.dao.generic.GenericDao;
import com.app.model.enums.Role;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    List<User> getTopUsersByPointsSum(int topRange);
    Role getRole(Long id);
}