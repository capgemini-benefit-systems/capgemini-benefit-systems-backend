package com.app.model.dao;

import javax.persistence.Query;

import com.app.model.Activity;
import com.app.model.ActivityResult;
import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityResultDaoImpl extends AbstractGenericDao<ActivityResult> implements ActivityResultDao  {


    @Override
    public ActivityResult getActivityResultByActivity(Long id) {

        ActivityResult activityResult =new ActivityResult();

        if(getEntityManager()!=null){
            Query query=getEntityManager().createQuery(
                "SELECT ar FROM " + geteClass().getCanonicalName() +" ar "+
                        "JOIN com.app.model.Activity a "+
                        "ON ar.activity.id=a.id " +
                        "WHERE ar.activity.id= :id"
            );
            query.setParameter("id", id);
            activityResult =(ActivityResult) query.getSingleResult();
        }

        return activityResult;
    }

    public List<Activity> getActivitiesByUserId(Long id){
        List<Activity> list=new ArrayList<>();
        if(getEntityManager()!=null){
            Query query=getEntityManager().createQuery(
                    "SELECT a FROM com.app.model.Activity a "+
                            "JOIN " + geteClass().getCanonicalName()+" ar "+
                            "ON ar.activity.id=a.id "+
                            "WHERE ar.user.id=:id"
            );
            query.setParameter("id",id);
            list=query.getResultList();
        }
        return list;
    }
}