package com.app.model.dao;

import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.User;
import com.app.model.dao.generic.GenericDao;
import com.app.model.enums.Permissions;

import java.util.List;

public interface ProjectMembersDao extends GenericDao<ProjectMembers> {
    List<Project> getProjectsByUserId(Long id);
    List<User> getUsersByProjectId(Long id);
    Permissions getPermissionsByUserIdAndProjectId(Long userId, Long projectId);
}