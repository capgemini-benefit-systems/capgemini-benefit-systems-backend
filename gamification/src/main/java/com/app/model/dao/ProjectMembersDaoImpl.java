package com.app.model.dao;

import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.User;
import com.app.model.dao.generic.AbstractGenericDao;
import com.app.model.enums.Permissions;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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
                            "ON pm.project.id = p.id " +
                            "WHERE pm.user.id = :id"
            );
            query.setParameter("id", id);
            list=query.getResultList();
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
                            "ON pm.user.id = u.id " +
                            "WHERE pm.project.id = :id"
            );
            query.setParameter("id", id);
            list=query.getResultList();
        }

        return list;


    }

    @Override
    public Permissions getPermissionsByUserIdAndProjectId(Long userId, Long projectId) {
        Permissions permissions = null;
        if(userId != null && userId >= 0 && projectId != null && projectId >= 0){
            Query query = getEntityManager().createQuery(
                    "SELECT pm.permissions " +
                            "FROM " + geteClass().getCanonicalName() + " pm " +
                            "WHERE pm.id.userId = :userId " +
                            "AND pm.id.projectId = :projectId"
            );
            query.setParameter("userId", userId);
            query.setParameter("projectId", projectId);
            try {
                permissions = (Permissions) query.getSingleResult();
            }
            catch (NoResultException ignored){}
        }
        return permissions;
    }
}