package com.app.model.dao;

import com.app.model.Project;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl  extends AbstractGenericDao<Project> implements ProjectDao  {
}