package com.app.model.dao;

import com.app.model.Activity;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityDaoImpl extends AbstractGenericDao<Activity> implements ActivityDao {

    @Override
    public String getPhotoPathByActivityId(Long id) {
        String path = "";
        if (getEntityManager() != null) {
            Query query = getEntityManager().createQuery(
                    "SELECT a.photo FROM " + geteClass().getCanonicalName() + " a WHERE a.id = :id"
            );
            query.setParameter("id", id);
            path = (String) query.getSingleResult();
        }
        return path;
    }


    public List<Activity> getActivitiesByProjectId(Long id) {
        List<Activity> list = new ArrayList<>();
        if (getEntityManager() != null) {
            Query query = getEntityManager().createQuery(
                    "SELECT a FROM " + geteClass().getCanonicalName() +
                            " a JOIN com.app.model.Project p " +
                            "ON p.id = :id " +
                            "WHERE a.project = :id"
            );
            query.setParameter("id", id);
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public List<Activity> getFinishedActivitiesByUserId(Long id, boolean finished) {
        List<Activity> list = new ArrayList<>();
        if (getEntityManager() != null) {
            Query query = getEntityManager().createQuery(
                    "SELECT a FROM " + geteClass().getCanonicalName() +
                            " a JOIN com.app.model.ActivityResult ar " +
                            "ON ar.activity.id = a.id " +
                            "JOIN com.app.model.User u " +
                            "ON u.id = ar.user.id " +
                            "WHERE u.id = :userId " +
                            "AND ar.dateOfReceipt IS " + (finished ? "NOT" : "") + " NULL"
            );
            query.setParameter("userId", id);
            list = query.getResultList();
        }
        return list;
    }

}