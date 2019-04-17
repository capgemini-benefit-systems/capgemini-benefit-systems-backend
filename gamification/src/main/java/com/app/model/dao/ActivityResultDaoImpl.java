package com.app.model.dao;

import javax.persistence.Query;
import com.app.model.ActivityResult;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityResultDaoImpl extends AbstractGenericDao<ActivityResult> implements ActivityResultDao  {


    @Override
    public ActivityResult getActivityResultByActivity(Long id) {

        ActivityResult activityResult =new ActivityResult();

        /*if(getEntityManager()!=null){
            Query query=getEntityManager().createQuery(){
                "SELECT  "
            };
            query.setParameter("id", id);
            activityResult =(ActivityResult) query.getSingleResult();
        }
*/
        return activityResult;
    }
}