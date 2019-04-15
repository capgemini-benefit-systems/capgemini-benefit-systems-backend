package com.app.controllers;

import com.app.controller.ProjectController;
import com.app.model.Project;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectDaoImpl;
import com.app.model.dao.ProjectMembersDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {

    @Test
    public void shouldAddProjectToDB(){
        /*ProjectMembersDao projectMembersDao
        ProjectDao projectDao = new ProjectDaoImpl();
        ProjectController projectController = new ProjectController(projectDao,project);
        Project sampleProject = new Project(1L,"","","",null,null,null,null,null,null);
        Assert.assertNotNull(projectController.addProject(sampleProject));*/
    }

}
