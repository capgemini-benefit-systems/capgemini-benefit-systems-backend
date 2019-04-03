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
    /*public List<Project> getProjectsByUserId(Long id) {
        List<Project> list = new ArrayList<>();
        if (getEntityManager() != null){
            Query query = getEntityManager().createQuery(
                    "SELECT p FROM com.app.model.Project p " +
                            "JOIN " + geteClass().getCanonicalName() + " pm " +
                            "ON pm.project_id = p.id " +
                            "WHERE pm.user_id = :id"
            );
            query.setParameter("id", id);
            query.getResultList();
        }
        return list;
    }*/
}