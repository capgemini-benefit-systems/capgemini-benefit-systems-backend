package com.app.controller;


import com.app.model.Project;
import com.app.model.dao.ActivityDao;
import com.app.model.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectDao projectDao;
    private final ActivityDao activityDao;

    @Autowired
    public ProjectController(ProjectDao projectDao, ActivityDao activityDao) {
        this.projectDao = projectDao;
        this.activityDao = activityDao;
    }

    @RequestMapping("/all")
    public List<Project> findAll(){
    return projectDao.findAll();
    }


}
