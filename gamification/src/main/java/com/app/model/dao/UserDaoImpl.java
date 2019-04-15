package com.app.model.dao;

import com.app.model.Project;
import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl  extends AbstractGenericDao<User> implements UserDao  {


    @Override
    public List<User> getTopUsersByPointsSum(int limit) {
        List<User> list = new ArrayList<>();
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT u FROM " + geteClass().getCanonicalName() + " u ORDER BY u.pointsSum DESC"
            );
            list = query.setMaxResults(limit).getResultList();
        }
        return list;
    }


}