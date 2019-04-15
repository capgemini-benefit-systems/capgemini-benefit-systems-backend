package com.app.model.dao;

import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectMembersDaoImpl extends AbstractGenericDao<ProjectMembers> implements ProjectMembersDao  {
    public List<Project> getProjectsByUserId(Long id) {
        List<Project> list = new ArrayList<>();
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT p FROM com.app.model.Project p " +
                            "JOIN " + geteClass().getCanonicalName() + " pm " +
                            "ON pm.projectId = p.id " +
                            "WHERE pm.userId = :id"
            );
            query.setParameter("id", id);
            query.getResultList();
        }
        return list;
    }

    @Override
    public List<User> getUsersByProjectId(Long id){
        List<User> list = new ArrayList<>();
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT u FROM com.app.model.User u " +
                            "JOIN " + geteClass().getCanonicalName() + " pm " +
                            "ON pm.userId = u.id " +
                            "WHERE pm.projectId = :id"
            );
            query.setParameter("id", id);
            query.getResultList();
        }
        return list;


    }
}