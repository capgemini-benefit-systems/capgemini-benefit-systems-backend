package com.app.model.dao;

import com.app.model.Activity;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDaoImpl  extends AbstractGenericDao<Activity> implements ActivityDao  {
}