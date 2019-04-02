package com.app.controller;


import com.app.model.Account;
import com.app.model.Project;
import com.app.model.User;
import com.app.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectDao projectDao;
    private final ActivityDao activityDao;
    private final ProjectMembersDao projectMembersDao;
    private final UserDao userDao;
    private final AccountDao accountDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, ActivityDao activityDao, ProjectMembersDao projectMembersDao, UserDao userDao, AccountDao accountDao) {
        this.projectDao = projectDao;
        this.activityDao = activityDao;
        this.projectMembersDao = projectMembersDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @RequestMapping("/all")
    public List<Project> findAll(){
    return projectDao.findAll();
    }




}
