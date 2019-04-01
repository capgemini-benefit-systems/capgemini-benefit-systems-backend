package com.app.model.dao;

import com.app.model.Award;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class AwardDaoImpl  extends AbstractGenericDao<Award> implements AwardDao  {
}