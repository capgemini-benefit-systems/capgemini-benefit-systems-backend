package com.app.model.dao;

import com.app.model.Project;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ProjectDaoImpl  extends AbstractGenericDao<Project> implements ProjectDao  {
    @Override
    public String getPhotoPathByProjectId(Long id) {
        String path = "";
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT p.photo FROM " + geteClass().getCanonicalName() + " p WHERE p.id = :id"
            );
            query.setParameter("id", id);
            path = (String) query.getSingleResult();
        }
        return path;
    }
}