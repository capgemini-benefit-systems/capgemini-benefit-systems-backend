package com.app.model.dao;

import com.app.model.Project;
import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import com.app.model.enums.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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

    @Override
    public Role getRole(Long id) {
        Role role = null;
        if(id != null && id >= 0){
            Query query = getEntityManager().createQuery(
                    "SELECT u.role FROM " + geteClass().getCanonicalName() + " u WHERE u.id = :id"
            );
            query.setParameter("id", id);
            try {
                role = (Role) query.getSingleResult();
            }
            catch (NoResultException ignored){}
        }
        return role;
    }


}