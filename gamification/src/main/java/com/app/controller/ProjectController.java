package com.app.controller;

import com.app.model.Project;
import com.app.model.ProjectMembers;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectMembersDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectMembersDaoImpl projectMembersDao;
    private ProjectDao projectDao;

    @Autowired
    public ProjectController(ProjectMembersDaoImpl projectMembersDao, ProjectDao projectDao) {
        this.projectMembersDao = projectMembersDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/user/{id}")
    public List<Project> getProjectsByUserId(@PathVariable Long id){
        return projectMembersDao.getProjectsByUserId(id);
    }

    @PostMapping("/add")
    public void addProjectPost(Project project){
        projectDao.insert(project);
    }

    @GetMapping("/addSamples")
    public void addSampleProjects(){
        Project p1 = new Project(null, "Proj1","Desc1",null,
                null,null,null,null,null,null);
        Project p2 = new Project(null, "Proj2","Desc2",null,
                null,null,null,null,null,null);
        Project p3 = new Project(null, "Proj3","Desc3",null,
                null,null,null,null,null,null);

        projectDao.insert(p1);
        projectDao.insert(p2);
        projectDao.insert(p3);

//
//        List<Project> projects = Arrays.asList(p1,p2,p3);
//        projects.forEach(projectDao::insert);
    }
}
